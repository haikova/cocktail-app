package olyarisu.github.com.cocktailapp.domain.cocktailsdetails

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteCocktail


class DefaultCocktailsDetailsModel(
    private val cocktailDetailsRepository: CocktailDetailsRepository
) : CocktailsDetailsModel {

    private lateinit var details: Cocktail
    override fun checkUserLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavCocktailDetails() = FavouriteCocktail(
        id = details.id,
        name = details.name,
        photoURL = details.photoURL
    )

    override fun getCocktailDetails(id: Int): Single<Cocktail> =
        cocktailDetailsRepository.getRemoteCocktailDetails(id)
            .doOnSuccess { details = it }
}