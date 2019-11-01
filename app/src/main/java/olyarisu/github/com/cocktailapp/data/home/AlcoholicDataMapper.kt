package olyarisu.github.com.cocktailapp.data.home

import olyarisu.github.com.cocktailapp.ALCOHOLIC
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Category

class AlcoholicDataMapper : Mapper<AlcoholicJson, List<Category>> {

    override fun map(value: AlcoholicJson): List<Category> {
        val list = mutableListOf<Category>()
        for (drink in value.drinks) {
            drink.strAlcoholic?.let { list.add(Category(ALCOHOLIC, it)) }
        }
        return list
    }

}