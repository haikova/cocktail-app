package olyarisu.github.com.cocktailapp.data.cocktaildetails

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailDetailsRepository
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

class DefaultCocktailDetailsRepository(
    private val cocktailDetailsDatasource: CocktailDetailsDatasource,
    private val cocktailDetailsDataMapper: Mapper<DrinkJson, Cocktail>
) : CocktailDetailsRepository {

    override fun getRemoteCocktailDetails(id: Int) =
        cocktailDetailsDatasource.getCocktailDetails(id)
            .flatMap { t -> Single.just(cocktailDetailsDataMapper.map(t)) }
}