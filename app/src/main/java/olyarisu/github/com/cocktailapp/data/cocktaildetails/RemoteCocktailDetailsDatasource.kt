package olyarisu.github.com.cocktailapp.data.cocktaildetails

import olyarisu.github.com.cocktailapp.data.network.NetworkService

class RemoteCocktailDetailsDatasource(
    private val networkService: NetworkService
) : CocktailDetailsDatasource {

    override fun getCocktailDetails(id: Int) = networkService.getCocktailService().getCocktail(id.toString())
}