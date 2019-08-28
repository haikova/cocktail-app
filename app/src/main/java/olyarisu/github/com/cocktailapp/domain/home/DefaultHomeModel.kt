package olyarisu.github.com.cocktailapp.domain.home

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.ALCOHOLIC
import olyarisu.github.com.cocktailapp.CATEGORY
import olyarisu.github.com.cocktailapp.GLASSES
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail


class DefaultHomeModel(
    private val homeRepository: HomeRepository
) : HomeModel {

    override fun getCategories() = homeRepository.getCategories()

    override fun getCocktailsListByCategory(category: Category): Single<List<Cocktail>> =
        when (category.idCategoryGroup) {
            ALCOHOLIC -> homeRepository.getCocktailListAlcholic(category.name)
            GLASSES -> homeRepository.getCocktailListGlasses(category.name)
            CATEGORY -> homeRepository.getCocktailListCategory(category.name)
            //TODO Fix it
            else -> homeRepository.getCocktailListAlcholic(category.name)
        }
}