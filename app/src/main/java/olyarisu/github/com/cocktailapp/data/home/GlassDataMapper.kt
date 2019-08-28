package olyarisu.github.com.cocktailapp.data.home

import olyarisu.github.com.cocktailapp.GLASSES
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Category

class GlassDataMapper : Mapper<GlassJson, List<Category>> {

    override fun map(value: GlassJson): List<Category> {
        val list = mutableListOf<Category>()
        for (drink in value.drinks) {
            drink.strGlass?.let { list.add(Category(GLASSES, it)) }
        }
        return list
    }

}