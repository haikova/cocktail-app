package olyarisu.github.com.cocktailapp.presentation.home

import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface HomeView : BaseView {
    fun gotoSearchScreen()
    fun showCategories(listCategory: List<Category>)
    fun showCocktailList(cocktails: List<Cocktail>)
    fun setCocktailsListTitle(title: String)
    fun hideProgress()
    fun showProgress()
}