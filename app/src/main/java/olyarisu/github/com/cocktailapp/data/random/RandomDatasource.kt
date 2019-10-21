package olyarisu.github.com.cocktailapp.data.random

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson

interface RandomDatasource {
    fun getRandomCocktail(): Single<DrinkJson>
}