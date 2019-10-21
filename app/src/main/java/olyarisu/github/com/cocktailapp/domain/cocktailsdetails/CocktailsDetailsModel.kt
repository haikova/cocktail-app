package olyarisu.github.com.cocktailapp.domain.cocktailsdetails

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface CocktailsDetailsModel {
    fun getCocktailDetails(id: Int): Single<Cocktail>
    fun checkUserLogin()
}