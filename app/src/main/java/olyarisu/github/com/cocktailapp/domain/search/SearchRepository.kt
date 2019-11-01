package olyarisu.github.com.cocktailapp.domain.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface SearchRepository {
    fun searchCocktails(searchText: String): Single<List<Cocktail>>
}