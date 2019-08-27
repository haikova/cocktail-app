package olyarisu.github.com.cocktailapp.data.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface SearchResultDatasource {
    fun serchCockatails(searchText: String): Single<DrinkJson>
}