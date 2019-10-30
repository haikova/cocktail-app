package olyarisu.github.com.cocktailapp.domain.favourite

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.db.DatabaseRepository
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
import olyarisu.github.com.cocktailapp.domain.login.AuthRepository

class DefaultFavouriteModel(
    private val authRepository: AuthRepository,
    private val dbRepository: DatabaseRepository

) : FavouriteModel {

    override fun getFavouriteCocktails(): Single<FavouriteList> =
        authRepository.userLoginId().flatMap {
            dbRepository.getUserFavourites(it)
        }

}