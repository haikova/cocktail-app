package olyarisu.github.com.cocktailapp.presentation.random

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.random.RandomModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsView

@InjectViewState
class RandomPresenter(
    val model: RandomModel
) : BasePresenter<RandomView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
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
    fun loadCocktailDetails() =
        model.getRandomCocktail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showProgressBar()
                viewState.disableTryAgainButton()
            }
            .doFinally {
                viewState.hideProgress()
                viewState.enableTryAgainButton()
            }
            .subscribe(
                { cocktail -> showCocktailDetails(cocktail) },
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