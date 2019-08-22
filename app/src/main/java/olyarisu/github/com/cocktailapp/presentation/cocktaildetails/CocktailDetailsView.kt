package olyarisu.github.com.cocktailapp.presentation.cocktaildetails

import olyarisu.github.com.cocktailapp.domain.entities.Ingredient
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface CocktailDetailsView : BaseView {
    fun setName(name: String)
    fun setInstruction(instruction: String?)
    fun setCategory(category: String)
    fun showProgressBar()
    fun hideProgress()
    fun showContainer()
    fun setIngredients(ingredients: List<Ingredient>?)
    fun setPhoto(photoURL: String?)
}