package olyarisu.github.com.cocktailapp.domain.entities

import olyarisu.github.com.cocktailapp.CockailCategory

data class Category (
    val idCategoryGroup: @CockailCategory Int,
    val name: String
)