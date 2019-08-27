package olyarisu.github.com.cocktailapp.data.search

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.search.SearchResultModelRepository

class DefaultSearchResulRepository(
    private val searchResultDatasource: SearchResultDatasource,
    private val searchResulDataMapper: Mapper<DrinkJson, List<Cocktail>>
) : SearchResultModelRepository {

    override fun serchCockatails(searchText: String): Single<List<Cocktail>> =
        searchResultDatasource.serchCockatails(searchText).flatMap {
            Single.just(
                searchResulDataMapper.map(it)
            )
        }
}