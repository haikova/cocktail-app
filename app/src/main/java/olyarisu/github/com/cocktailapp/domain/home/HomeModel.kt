package olyarisu.github.com.cocktailapp.domain.home

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface HomeModel {
    abstract fun searchCocktail(searchText: String): Single<List<Cocktail>>
}