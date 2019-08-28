package olyarisu.github.com.cocktailapp.data.search

import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient

class SearhResultDataMapper : Mapper<DrinkJson, List<Cocktail>> {

    override fun map(value: DrinkJson): List<Cocktail> {
        val cocktailList = arrayListOf<Cocktail>()

        for (drink in value.drinks) {
            //TODO fix it
            val ingredientsList = arrayListOf<Ingredient>()
            if (!drink.strIngredient1.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient1, drink.strMeasure1))
            }
            if (!drink.strIngredient2.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient2, drink.strMeasure2))
            }
            if (!drink.strIngredient3.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient3, drink.strMeasure3))
            }
            if (!drink.strIngredient4.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient4, drink.strMeasure4))
            }
            if (!drink.strIngredient5.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient5, drink.strMeasure5))
            }
            if (!drink.strIngredient6.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient6, drink.strMeasure6))
            }
            if (!drink.strIngredient7.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient7, drink.strMeasure7))
            }
            if (!drink.strIngredient8.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient8, drink.strMeasure8))
            }
            if (!drink.strIngredient9.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient9, drink.strMeasure9))
            }
            if (!drink.strIngredient10.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient10, drink.strMeasure10))
            }
            if (!drink.strIngredient11.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient11, drink.strMeasure11))
            }
            if (!drink.strIngredient12.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient12, drink.strMeasure12))
            }
            if (!drink.strIngredient13.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient13, drink.strMeasure13))
            }
            if (!drink.strIngredient14.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient14, drink.strMeasure14))
            }
            if (!drink.strIngredient15.isNullOrBlank()) {
                ingredientsList.add(Ingredient(drink.strIngredient15, drink.strMeasure15))
            }

            cocktailList.add(
                Cocktail(
                    id = drink.idDrink.toInt(),
                    name = drink.strDrink,
                    instruction = drink.strInstructions,
                    ingredients = ingredientsList,
                    category = drink.strCategory,
                    alcoholic = drink.strAlcoholic,
                    glass = drink.strGlass,
                    photoURL = drink.strDrinkThumb
                )
            )

        }

        return cocktailList
    }
}