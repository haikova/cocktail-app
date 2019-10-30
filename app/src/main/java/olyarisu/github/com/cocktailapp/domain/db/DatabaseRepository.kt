package olyarisu.github.com.cocktailapp.domain.db

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList

interface DatabaseRepository {
    fun getUserFavourites(userId: String): Single<FavouriteList>
}