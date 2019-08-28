package olyarisu.github.com.cocktailapp.data.home

import io.reactivex.Observable
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.home.HomeRepository

class DefaultHomeRepository(
    private val homeRemoteDatasource: HomeRemoteDatasource,
    private val alcholicDataMapper: Mapper<AlcoholicJson, List<Category>>,
    private val categoryDataMapper: Mapper<CategoryJson, List<Category>>,
    private val glassDataMapper: Mapper<GlassJson, List<Category>>,
    private val cocktailDataMapper: Mapper<DrinkJson, List<Cocktail>>
) : HomeRepository {

    override fun getCocktailListAlcholic(category: String): Single<List<Cocktail>> =
        homeRemoteDatasource.getCocktailListAlcholic(category).flatMap {
            Single.just(
                cocktailDataMapper.map(it)
            )
        }
    override fun getCocktailListCategory(category: String): Single<List<Cocktail>> =
        homeRemoteDatasource.getCocktailListCategory(category).flatMap {
            Single.just(
                cocktailDataMapper.map(it)
            )
        }
    override fun getCocktailListGlasses(category: String): Single<List<Cocktail>> =
        homeRemoteDatasource.getCocktailListGlasses(category).flatMap {
            Single.just(
                cocktailDataMapper.map(it)
            )
        }

/*    override fun getCategories(): Single<List<Category>> = getAlcoholicCategories()*/

    override fun getCategories(): Observable<List<Category>> =
        Observable.merge(
            getAlcoholicCategories().toObservable(),
            getCategoryCategories().toObservable(),
            getGlassCategories().toObservable()
        )

    private fun getAlcoholicCategories(): Single<List<Category>> =
        homeRemoteDatasource.getAlcoholicCategories().flatMap {
            Single.just(
                alcholicDataMapper.map(it)
            )
        }

    private fun getCategoryCategories(): Single<List<Category>> =
        homeRemoteDatasource.getCategoryCategories().flatMap {
            Single.just(
                categoryDataMapper.map(it)
            )
        }

    private fun getGlassCategories(): Single<List<Category>> =
        homeRemoteDatasource.getGlassCategories().flatMap {
            Single.just(
                glassDataMapper.map(it)
            )
        }
}