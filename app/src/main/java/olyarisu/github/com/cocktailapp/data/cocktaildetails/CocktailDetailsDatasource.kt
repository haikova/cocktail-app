package olyarisu.github.com.cocktailapp.data.cocktaildetails

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson

interface CocktailDetailsDatasource {
    fun getCocktailDetails(id: Int): Single<DrinkJson>
}