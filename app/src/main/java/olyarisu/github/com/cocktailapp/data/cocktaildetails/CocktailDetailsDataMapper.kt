package olyarisu.github.com.cocktailapp.data.cocktaildetails

import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient

class CocktailDetailsDataMapper : Mapper<DrinkJson, Cocktail> {
    override fun map(value: DrinkJson): Cocktail {

        val drink = value.drinks.first()

        //TODO fix it
        val ingredientsList = arrayListOf<Ingredient>()
        if (drink.strIngredient1.isNotBlank()) {
            ingredientsList.add(Ingredient(drink.strIngredient1, drink.strMeasure1))
        }
        if (drink.strIngredient2.isNotBlank()) {
            ingredientsList.add(Ingredient(drink.strIngredient2, drink.strMeasure2))
        }
        if (drink.strIngredient3.isNotBlank()) {
            ingredientsList.add(Ingredient(drink.strIngredient3, drink.strMeasure3))
        }
        if (drink.strIngredient4.isNotBlank()) {
            ingredientsList.add(Ingredient(drink.strIngredient4, drink.strMeasure4))
        }
        if (drink.strIngredient5.isNotBlank()) {
            ingredientsList.add(Ingredient(drink.strIngredient5, drink.strMeasure5))
        }

        return Cocktail(
            id = drink.idDrink.toInt(),
            name = drink.strDrink,
            instruction = drink.strInstructions,
            ingredients = ingredientsList,
            category = drink.strCategory,
            alcoholic = drink.strAlcoholic,
            glass = drink.strGlass,
            photoURL = drink.strDrinkThumb
        )
    }
}