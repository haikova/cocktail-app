package olyarisu.github.com.cocktailapp.di

import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDataMapper
import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.cocktaildetails.DefaultCocktailDetailsRepository
import olyarisu.github.com.cocktailapp.data.cocktaildetails.RemoteCocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.dto.DrinkJson
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.data.network.NetworkService
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailDetailsRepository
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.DefaultCocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsPresenter
import org.koin.dsl.module

val applicationModule = module {
    factory { CocktailDetailsPresenter(get()) }
    single { DefaultCocktailsDetailsModel(get()) as CocktailsDetailsModel }
    single { DefaultCocktailDetailsRepository(get(), get()) as CocktailDetailsRepository }
    single { RemoteCocktailDetailsDatasource(get()) as CocktailDetailsDatasource }
    single { CocktailDetailsDataMapper() as Mapper<DrinkJson, Cocktail> }
    single { NetworkService() }

}
