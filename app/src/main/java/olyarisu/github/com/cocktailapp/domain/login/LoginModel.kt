package olyarisu.github.com.cocktailapp.domain.login

import io.reactivex.Completable

interface LoginModel {
    fun authWithGoogle(idToken: String): Completable
}