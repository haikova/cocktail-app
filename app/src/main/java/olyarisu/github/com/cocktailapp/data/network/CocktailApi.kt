package olyarisu.github.com.cocktailapp.data.network

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    fun searchCocktailByName(@Query("s") searchText: String): Single<DrinkJson>

    @GET("lookup.php")
    fun getCocktail(@Query("i") idCocktail: String): Single<DrinkJson>

    @GET("random.php")
    fun getRandomCocktail(): Single<DrinkJson>

    @GET("filter.php")
    fun getCocktailsListByAlcoholic(@Query("a") alcoholic: String): Single<DrinkJson>

    @GET("filter.php")
    fun getCocktailsListByCategory(@Query("c") category: String): Single<DrinkJson>

    @GET("filter.php")
    fun getCocktailsListByGlass(@Query("g") glass: String): Single<DrinkJson>

    @GET("list.php?c=list")
    fun getCategoriesList(): Single<CategoryJson>

    @GET("list.php?g=list")
    fun getGlassessList(): Single<GlassJson>

    @GET("list.php?a=list")
    fun getAlcoholicList(): Single<AlcoholicJson>
}
