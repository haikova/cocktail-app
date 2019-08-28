package olyarisu.github.com.cocktailapp.domain.home

import io.reactivex.Observable
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface HomeRepository {
    fun getCategories(): Observable<List<Category>>
    fun getCocktailListAlcholic(category: String): Single<List<Cocktail>>
    fun getCocktailListCategory(category: String): Single<List<Cocktail>>
    fun getCocktailListGlasses(category: String): Single<List<Cocktail>>
}