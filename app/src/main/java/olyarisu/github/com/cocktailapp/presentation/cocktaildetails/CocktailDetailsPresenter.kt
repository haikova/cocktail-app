package olyarisu.github.com.cocktailapp.presentation.cocktaildetails

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.COCKTAIL_APP
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class CocktailDetailsPresenter(
    private val model: CocktailsDetailsModel,
    val id: Int
) : BasePresenter<CocktailDetailsView>() {

    override fun attachView(view: CocktailDetailsView?) {
        super.attachView(view)
        loadCocktailDetails()
    }

    fun favouriteButtonPressed(isChecked: Boolean) {
        if (checkUserLoginIn()) {
            when (isChecked) {
                true -> addToFavourites()
                false -> removeFromFavourites()
            }
        } else {
            viewState.gotoLoginScreen()
        }
    }

    private fun checkInFavourites() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        uid?.let {
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val favourites = document.toObject(FavouriteList::class.java)
                        val id = model.getFavCocktailDetails().id
                        favourites?.favourites?.forEach {
                            if (it.id == id) {
                                viewState.setFavButtonPressed()
                            }
                        }
                    } else {
                        Log.d(COCKTAIL_APP, "No such document")
                    }
                }
        }
    }

    private fun checkUserLoginIn(): Boolean {
        // TODO move to model
        //model.checkUserLogin()
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            return true
        } ?: return false
    }

    private fun removeFromFavourites() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        uid?.let {
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .document(uid)
                .update("favourites", FieldValue.arrayRemove(model.getFavCocktailDetails()))
        }
    }

    private fun addToFavourites() {
        // TODO move to model

        val firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        uid?.let {
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .document(uid)
                .update("favourites", FieldValue.arrayUnion(model.getFavCocktailDetails()))
        }
    }

    //TODO fix dispose()
    private fun loadCocktailDetails() =
        model.getCocktailDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .doFinally { viewState.hideProgress() }
            .subscribe(
                { cocktail -> showCocktailDetails(cocktail)
                    checkInFavourites()},
                { error -> viewState.showError(error) })

    private fun showCocktailDetails(cocktail: Cocktail) {
        viewState.setName(cocktail.name)
        viewState.setInstruction(cocktail.instruction)
        viewState.setCategory(cocktail.category + ", " + cocktail.glass + ", " + cocktail.alcoholic)
        viewState.showContainer()
        viewState.setIngredients(cocktail.ingredients)
        viewState.setPhoto(cocktail.photoURL)
    }
}