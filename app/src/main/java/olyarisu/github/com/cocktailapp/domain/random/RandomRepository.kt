package olyarisu.github.com.cocktailapp.domain.random

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface RandomRepository {

    fun getRemoteRandomCocktail(): Single<Cocktail>
}