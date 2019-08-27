package olyarisu.github.com.cocktailapp.domain.home

import io.reactivex.Single
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

class DefaultHomeModel : HomeModel{
    override fun searchCocktail(searchText: String): Single<List<Cocktail>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}