package olyarisu.github.com.cocktailapp.data.home

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson
import olyarisu.github.com.cocktailapp.data.network.NetworkService

class DefaultHomeRemoteDatasource(
    private val networkService: NetworkService
) : HomeRemoteDatasource {
    override fun getCocktailListGlasses(category: String): Single<DrinkJson> =
        networkService.getCocktailService().getCocktailsListByGlass(category)

    override fun getCocktailListCategory(category: String): Single<DrinkJson> =
        networkService.getCocktailService().getCocktailsListByCategory(category)

    override fun getCocktailListAlcoholic(category: String): Single<DrinkJson> =
        networkService.getCocktailService().getCocktailsListByAlcoholic(category)

    override fun getAlcoholicCategories(): Single<AlcoholicJson> =
        networkService.getCocktailService().getAlcoholicList()

    override fun getCategoryCategories(): Single<CategoryJson> =
        networkService.getCocktailService().getCategoriesList()

    override fun getGlassCategories(): Single<GlassJson> =
        networkService.getCocktailService().getGlassessList()
}