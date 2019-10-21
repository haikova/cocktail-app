package olyarisu.github.com.cocktailapp.domain.random

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

class DefaultRandomModel(
    private val randomRepository: RandomRepository
) : RandomModel {

    override fun getRandomCocktail() = randomRepository.getRemoteRandomCocktail()
}