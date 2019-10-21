package olyarisu.github.com.cocktailapp.data.random

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.random.RandomRepository

class DefaultRandomRepository(
    private val cocktailDetailsDatasource: RandomDatasource,
    private val cocktailDetailsDataMapper: Mapper<DrinkJson, Cocktail>
) : RandomRepository {

    override fun getRemoteRandomCocktail() =
        cocktailDetailsDatasource.getRandomCocktail()
            .flatMap { t -> Single.just(cocktailDetailsDataMapper.map(t)) }
}