package olyarisu.github.com.cocktailapp.data.home

import olyarisu.github.com.cocktailapp.CATEGORY
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Category

class CategoryDataMapper : Mapper<CategoryJson, List<Category>> {

    override fun map(value: CategoryJson): List<Category> {
        val list = mutableListOf<Category>()
        for (drink in value.drinks) {
            drink.strCategory?.let { list.add(Category(CATEGORY, it)) }
        }
        return list
    }

}