package olyarisu.github.com.cocktailapp.domain.cocktailsdetails

import io.reactivex.Single

class DefaultCocktailsDetailsModel(
    private val cocktailDetailsRepository: CocktailDetailsRepository
) : CocktailsDetailsModel{

    override fun getCocktailDetails(id: Int) =
        cocktailDetailsRepository.getRemoteCocktailDetails(id)
}