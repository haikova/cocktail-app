package olyarisu.github.com.cocktailapp.domain.home

import io.reactivex.Observable
import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

interface HomeModel {
    fun getCategories(): Observable<List<Category>>
    fun getCocktailsListByCategory(category: Category): Single<List<Cocktail>>
}