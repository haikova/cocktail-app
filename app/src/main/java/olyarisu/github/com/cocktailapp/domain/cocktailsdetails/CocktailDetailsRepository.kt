package olyarisu.github.com.cocktailapp.domain.cocktailsdetails

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface CocktailDetailsRepository {
    fun getRemoteCocktailDetails(id: Int): Single<Cocktail>
}