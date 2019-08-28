package olyarisu.github.com.cocktailapp.data.home

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson

interface HomeRemoteDatasource {
    fun getAlcoholicCategories(): Single<AlcoholicJson>
    fun getCocktailListAlcholic(category: String): Single<DrinkJson>
    fun getCocktailListCategory(category: String): Single<DrinkJson>
    fun getCocktailListGlasses(category: String): Single<DrinkJson>
    fun getGlassCategories(): Single<GlassJson>
    fun getCategoryCategories(): Single<CategoryJson>
}