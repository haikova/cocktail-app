package olyarisu.github.com.cocktailapp.presentation.search

import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface SearchView : BaseView {

    fun showSearchResult(searchResult: List<Cocktail>)
    fun hideProgress()
    fun showProgressBar()
    fun hideKeyboard()
}
