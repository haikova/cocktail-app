package olyarisu.github.com.cocktailapp.domain.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface SearchResultModelRepository {
    fun serchCockatails(searchText: String): Single<List<Cocktail>>
}