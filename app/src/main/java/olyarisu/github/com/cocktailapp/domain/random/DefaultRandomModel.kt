package olyarisu.github.com.cocktailapp.domain.random

import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteCocktail

class DefaultRandomModel(
    private val randomRepository: RandomRepository
) : RandomModel {

    private lateinit var details: Cocktail

    override fun getFavCocktailDetails() = FavouriteCocktail(
        id = details.id,
        name = details.name,
        photoURL = details.photoURL
    )

    override fun getRandomCocktail() = randomRepository.getRemoteRandomCocktail()
        .doOnSuccess { details = it }
}