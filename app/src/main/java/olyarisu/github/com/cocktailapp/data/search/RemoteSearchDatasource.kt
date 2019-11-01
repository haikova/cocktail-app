package olyarisu.github.com.cocktailapp.data.search

import olyarisu.github.com.cocktailapp.data.network.NetworkService

class RemoteSearchDatasource(
    private val networkService: NetworkService
) : SearchDatasource {

    override fun searchCocktails(searchText: String) =
        networkService.getCocktailService().searchCocktailByName(searchText)
}