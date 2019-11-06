package olyarisu.github.com.cocktailapp.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.COCKTAIL_APP
import olyarisu.github.com.cocktailapp.data.LoginException
import olyarisu.github.com.cocktailapp.domain.login.AuthRepository

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun userLoginId(): Single<String> = Single.create {
        firebaseAuth.currentUser?.uid?.let { id ->
            it.onSuccess(id)
        } ?: it.onError(LoginException())
    }

    override fun authWithGoogle(idToken: String): Completable =
        Completable.create {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Login", "signInWithCredential:success")
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            checkUserInDB(user)
                        }
                        it.onComplete()
                    } else {
                        Log.w("Login", "signInWithCredential:failure", task.exception)
                        it.onError(task.exception ?: Exception())
                    }
                }
        }

    private fun checkUserInDB(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d(COCKTAIL_APP, "DocumentSnapshot data: ${document.data}")
                } else {
                    val favourites = hashMapOf(
                        "favourites" to arrayListOf<String>()
                    )
                    db.collection("users")
                        .document(user.uid)
                        .set(favourites)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(COCKTAIL_APP, "get failed with ", exception)
            }
    }
}