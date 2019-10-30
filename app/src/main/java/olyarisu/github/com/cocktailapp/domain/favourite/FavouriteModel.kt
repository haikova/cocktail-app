package olyarisu.github.com.cocktailapp.domain.favourite

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList

interface FavouriteModel {
    fun getFavouriteCocktails(): Single<FavouriteList>
}