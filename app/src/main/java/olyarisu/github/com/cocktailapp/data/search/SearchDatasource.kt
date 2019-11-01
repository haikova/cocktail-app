package olyarisu.github.com.cocktailapp.data.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson

interface SearchDatasource {
    fun searchCocktails(searchText: String): Single<DrinkJson>
}