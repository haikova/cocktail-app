package olyarisu.github.com.cocktailapp.data.network

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php?s={name}")
    fun searchCocktailByName(@Path("name") name : String): Any

    @GET("search.php?i={name}")
    fun searchIngredientByName(@Path("name") name : String): Any

    @GET("lookup.php")
    fun getCocktail(@Query("i") idCocktail : String): Single<DrinkJson>

    @GET("lookup.php?id={id}")
    fun getIngredient(@Path("id") idIngredient : Int): Any

    @GET("random.php")
    fun getRandomCocktail(): Any

    //PATREON
    @GET("popular.php")
    fun getPopularCocktailsList(): Any

    //PATREON
    @GET("recent.php")
    fun getRecentCocktailsList(): Any

    @GET("filter.php?i={ingredient}")
    fun searchCocktailByIngredient(@Path("ingredient") ingredient : String): Any

    //PATREON
    @GET("filter.php?i={ingredient}")
    fun searchCocktailByIngredients(@Path("ingredients") ingredients : String): Any

    @GET("filter.php?a={alcoholic}")
    fun getCocktailsListByAlcoholic(@Path("alcoholic") alcoholic : String): Any

    @GET("filter.php?c={category}")
    fun getCocktailsListByCategory(@Path("category") category : String): Any

    @GET("filter.php?g={glass}")
    fun getCocktailsListByGlass(@Path("glass") glass : String): Any

    //Next 4 request should like config information on the beginning using appуу
    @GET("list.php?c=list")
    fun getCategoriesList(): Any

    @GET("list.php?g=list")
    fun getGlassessList(): Any

    @GET("list.php?i=list")
    fun getIngredientssList(): Any

    @GET("list.php?a=list")
    fun getAlcoholicList(): Any


    companion object Factory {
        fun create(): CocktailApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                    .build()

            return retrofit.create(CocktailApi::class.java)
        }
    }
}

/*
@Retention(AnnotationRetention.SOURCE)
@StringDef({
    ORDINARY_DRINK,
    COCKTAIL
})
annotation class DrinkCategory {
    const val ORDINARY_DRINK = "Ordinary Drink"
    const val COCKTAIL = "Cocktail"
    const val  "Milk \/ Float \/ Shake"
    const val  "Other\/Unknown"
    const val  "Cocoa"
    const val  "Shot"
    const val  "Coffee \/ Tea"
    const val  "Homemade Liqueur"
    const val  "Punch \/ Party Drink"
    const val  "Beer"
    const val  "Soft Drink \/ Soda"
}*/
