package olyarisu.github.com.cocktailapp.presentation.favouritelist

import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface FavouriteCocktailsView : BaseView {
    fun gotoLoginScreen()
    fun showFavouriteCocktails(list: FavouriteList)
    fun showProgress()
    fun hideProgress()
    fun showTip()
}