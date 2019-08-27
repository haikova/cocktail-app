package olyarisu.github.com.cocktailapp.data.search

import olyarisu.github.com.cocktailapp.data.network.NetworkService

class RemoteSearchResultDatasource(
    private val networkService: NetworkService
) : SearchResultDatasource {

    override fun serchCockatails(searchText: String) =
        networkService.getCocktailService().searchCocktailByName(searchText)
}