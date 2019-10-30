package olyarisu.github.com.cocktailapp.domain.login

import io.reactivex.Single

interface AuthRepository {
    fun userLoginId(): Single<String>
}