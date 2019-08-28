package olyarisu.github.com.cocktailapp.data.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson

interface SearchResultDatasource {
    fun serchCockatails(searchText: String): Single<DrinkJson>
}