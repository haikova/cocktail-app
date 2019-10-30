package olyarisu.github.com.cocktailapp.data.firebase

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.LoginException
import olyarisu.github.com.cocktailapp.domain.login.AuthRepository

class FirebaseAuthRepository(
    private val firebaseAuth : FirebaseAuth
) : AuthRepository {

    override fun userLoginId(): Single<String> = Single.create {
        firebaseAuth.currentUser?.uid?.let { id ->
            it.onSuccess(id)
        } ?: it.onError(LoginException())
    }
}