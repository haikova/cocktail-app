package olyarisu.github.com.cocktailapp.di

import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDataMapper
import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.cocktaildetails.DefaultCocktailDetailsRepository
import olyarisu.github.com.cocktailapp.data.cocktaildetails.RemoteCocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.data.network.NetworkService
import olyarisu.github.com.cocktailapp.data.search.DefaultSearchResulRepository
import olyarisu.github.com.cocktailapp.data.search.RemoteSearchResultDatasource
import olyarisu.github.com.cocktailapp.data.search.SearchResultDatasource
import olyarisu.github.com.cocktailapp.data.search.SearhResultDataMapper
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailDetailsRepository
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.DefaultCocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.search.DefaultSearchResultModel
import olyarisu.github.com.cocktailapp.domain.search.SearchResultModel
import olyarisu.github.com.cocktailapp.domain.search.SearchResultModelRepository
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsPresenter
import olyarisu.github.com.cocktailapp.presentation.search.SearchResultPresenter
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val applicationModule = module {
    single { NetworkService() }
    factory { (id: Int) -> CocktailDetailsPresenter(get(), id) }
    single { DefaultCocktailsDetailsModel(get()) as CocktailsDetailsModel }
    single { DefaultCocktailDetailsRepository(get(), get(StringQualifier("details"))) as CocktailDetailsRepository }
    single { RemoteCocktailDetailsDatasource(get()) as CocktailDetailsDatasource }
    single(qualifier = StringQualifier("details")) { CocktailDetailsDataMapper() as Mapper<DrinkJson, Cocktail> }

    //Search
    factory { SearchResultPresenter(get()) }
    single { DefaultSearchResultModel(get()) as SearchResultModel }
    single { DefaultSearchResulRepository(get(), get(StringQualifier("search"))) as SearchResultModelRepository }
    single { RemoteSearchResultDatasource(get()) as SearchResultDatasource }
    single(qualifier = StringQualifier("search")){ SearhResultDataMapper() as Mapper<DrinkJson, List<Cocktail>> }
}
