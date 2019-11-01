package olyarisu.github.com.cocktailapp.data.random

import olyarisu.github.com.cocktailapp.data.network.NetworkService

class RemoteRandomDatasource(
    private val networkService: NetworkService
) : RandomDatasource {
    override fun getRandomCocktail() = networkService.getCocktailService().getRandomCocktail()
}