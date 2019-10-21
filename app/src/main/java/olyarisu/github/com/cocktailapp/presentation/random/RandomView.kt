package olyarisu.github.com.cocktailapp.presentation.random

import olyarisu.github.com.cocktailapp.domain.entities.Ingredient
import olyarisu.github.com.cocktailapp.presentation.base.BaseView

interface RandomView : BaseView{
    fun setName(name: String?)
    fun setInstruction(instruction: String?)
    fun setCategory(category: String)
    fun showProgressBar()
    fun hideProgress()
    fun showContainer()
    fun setIngredients(ingredients: List<Ingredient>?)
    fun setPhoto(photoURL: String?)
    fun enableTryAgainButton()
    fun disableTryAgainButton()
}