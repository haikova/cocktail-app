package olyarisu.github.com.cocktailapp.di

import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDataMapper
import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.cocktaildetails.DefaultCocktailDetailsRepository
import olyarisu.github.com.cocktailapp.data.cocktaildetails.RemoteCocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson
import olyarisu.github.com.cocktailapp.data.home.*
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.data.network.NetworkService
import olyarisu.github.com.cocktailapp.data.random.DefaultRandomRepository
import olyarisu.github.com.cocktailapp.data.random.RandomDatasource
import olyarisu.github.com.cocktailapp.data.random.RemoteRandomDatasource
import olyarisu.github.com.cocktailapp.data.search.DefaultSearchResulRepository
import olyarisu.github.com.cocktailapp.data.search.RemoteSearchResultDatasource
import olyarisu.github.com.cocktailapp.data.search.SearchResultDatasource
import olyarisu.github.com.cocktailapp.data.search.SearhResultDataMapper
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailDetailsRepository
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.DefaultCocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.home.DefaultHomeModel
import olyarisu.github.com.cocktailapp.domain.home.HomeModel
import olyarisu.github.com.cocktailapp.domain.home.HomeRepository
import olyarisu.github.com.cocktailapp.domain.random.DefaultRandomModel
import olyarisu.github.com.cocktailapp.domain.random.RandomModel
import olyarisu.github.com.cocktailapp.domain.random.RandomRepository
import olyarisu.github.com.cocktailapp.domain.search.DefaultSearchResultModel
import olyarisu.github.com.cocktailapp.domain.search.SearchResultModel
import olyarisu.github.com.cocktailapp.domain.search.SearchResultModelRepository
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsPresenter
import olyarisu.github.com.cocktailapp.presentation.home.HomePresenter
import olyarisu.github.com.cocktailapp.presentation.random.RandomPresenter
import olyarisu.github.com.cocktailapp.presentation.search.SearchResultPresenter
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val applicationModule = module {

    single { NetworkService() }

    //Home
    factory { HomePresenter(get()) }
    single { DefaultHomeModel(get()) as HomeModel }
    single {
        DefaultHomeRepository(
            get(),
            get(StringQualifier("alcoholic")),
            get(StringQualifier("category")),
            get(StringQualifier("glass")),
            get(StringQualifier("search"))
        ) as HomeRepository
    }
    single { DefaultHomeRemoteDatasource(get()) as HomeRemoteDatasource }
    single(qualifier = StringQualifier("alcoholic")) { AlcholicDataMapper() as Mapper<AlcoholicJson, List<Category>> }
    single(qualifier = StringQualifier("category")) { CategoryDataMapper() as Mapper<CategoryJson, List<Category>> }
    single(qualifier = StringQualifier("glass")) { GlassDataMapper() as Mapper<GlassJson, List<Category>> }

    //Cocktail Details
    factory { (id: Int) -> CocktailDetailsPresenter(get(), id) }
    single { DefaultCocktailsDetailsModel(get()) as CocktailsDetailsModel }
    single {
        DefaultCocktailDetailsRepository(
            get(),
            get(StringQualifier("details"))
        ) as CocktailDetailsRepository
    }
    single { RemoteCocktailDetailsDatasource(get()) as CocktailDetailsDatasource }
    single(qualifier = StringQualifier("details")) { CocktailDetailsDataMapper() as Mapper<DrinkJson, Cocktail> }

    //Search
    factory { SearchResultPresenter(get()) }
    single { DefaultSearchResultModel(get()) as SearchResultModel }
    single {
        DefaultSearchResulRepository(
            get(),
            get(StringQualifier("search"))
        ) as SearchResultModelRepository
    }
    single { RemoteSearchResultDatasource(get()) as SearchResultDatasource }
    single(qualifier = StringQualifier("search")) { SearhResultDataMapper() as Mapper<DrinkJson, List<Cocktail>> }

    //Random cocktail
    factory { RandomPresenter(get()) }
    single { DefaultRandomModel(get()) as RandomModel }
    single {
        DefaultRandomRepository(
            get(),
            get(StringQualifier("random"))
        ) as RandomRepository
    }
    single { RemoteRandomDatasource(get()) as RandomDatasource }
    single(qualifier = StringQualifier("random")) { CocktailDetailsDataMapper() as Mapper<DrinkJson, Cocktail> }
}
