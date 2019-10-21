package olyarisu.github.com.cocktailapp.presentation.favouritelist

import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface FavouriteCocktalsView : BaseView {
    fun gotoLoginScreen()
    fun showFavouriteCocktails(list: MutableList<Cocktail>)
    fun showTip()
}