package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class FavouriteCocktalsPresenter : BasePresenter<FavouriteCocktalsView>() {

    override fun attachView(view: FavouriteCocktalsView?) {
        super.attachView(view)
        checkLogin()
    }

    private fun checkLogin() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            checkFavouriteCocktails()
        } ?: viewState.gotoLoginScreen()
    }

    private fun checkFavouriteCocktails() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Favourites cocktails")
            .get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    viewState.showTip()
                } else {
                    val listCocktails = mutableListOf<Cocktail>()
                    for (document in result) {
                        val cocktail = document.toObject(Cocktail::class.java)
                        listCocktails.add(cocktail)
                    }
                    viewState.showFavouriteCocktails(listCocktails)
                }
            }
            .addOnFailureListener { exception ->
            }

    }
}