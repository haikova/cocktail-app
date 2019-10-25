package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import olyarisu.github.com.cocktailapp.COCKTAIL_APP
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
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
            val uid = firebaseAuth.currentUser?.uid
            uid?.let {
                val db = FirebaseFirestore.getInstance()
                db.collection("users")
                    .document(uid)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val favourites = document.toObject(FavouriteList::class.java)
                            Log.d(COCKTAIL_APP, "DocumentSnapshot data: ${document.data}")
                            Log.d(COCKTAIL_APP, favourites.toString())
                            favourites?.let { it1 -> viewState.showFavouriteCocktails(it1) }
                        } else {
                            Log.d(COCKTAIL_APP, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(COCKTAIL_APP, "get failed with ", exception)
                    }
            }
        } ?: viewState.gotoLoginScreen()
    }
}