package olyarisu.github.com.cocktailapp.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.EmptyDBDocumentException
import olyarisu.github.com.cocktailapp.domain.db.DatabaseRepository
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList

class FirestoreRepository(
    private val firebaseFirestore : FirebaseFirestore
) : DatabaseRepository{

    override fun getUserFavourites(userId: String): Single<FavouriteList> =
        Single.create {
            firebaseFirestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val list = document.toObject(FavouriteList::class.java)
                    if (list != null && !list.favourites.isNullOrEmpty()) {
                        it.onSuccess(list)
                    } else {
                        it.onError(EmptyDBDocumentException())
                    }
                }
                .addOnFailureListener { exception ->
                    it.onError(exception)
                }
        }
}