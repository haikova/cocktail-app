package olyarisu.github.com.cocktailapp.domain.entities

import olyarisu.github.com.cocktailapp.CocktailCategory

data class Category (
    val idCategoryGroup: @CocktailCategory Int,
    val name: String
)