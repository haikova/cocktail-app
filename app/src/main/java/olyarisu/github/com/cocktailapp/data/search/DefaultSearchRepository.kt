package olyarisu.github.com.cocktailapp.data.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.search.SearchRepository

class DefaultSearchRepository(
    private val searchResultDatasource: SearchDatasource,
    private val searchResultDataMapper: Mapper<DrinkJson, List<Cocktail>>
) : SearchRepository {

    override fun searchCocktails(searchText: String): Single<List<Cocktail>> =
        searchResultDatasource.searchCocktails(searchText).flatMap {
            Single.just(
                searchResultDataMapper.map(it)
            )
        }
}