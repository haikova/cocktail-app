package olyarisu.github.com.cocktailapp.domain.random

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteCocktail

interface RandomModel {
    fun getRandomCocktail(): Single<Cocktail>
    fun getFavCocktailDetails(): FavouriteCocktail
}