package olyarisu.github.com.cocktailapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDataMapper
import olyarisu.github.com.cocktailapp.data.cocktaildetails.CocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.cocktaildetails.DefaultCocktailDetailsRepository
import olyarisu.github.com.cocktailapp.data.cocktaildetails.RemoteCocktailDetailsDatasource
import olyarisu.github.com.cocktailapp.data.dto.alcoholic.AlcoholicJson
import olyarisu.github.com.cocktailapp.data.dto.category.CategoryJson
import olyarisu.github.com.cocktailapp.data.dto.drink.DrinkJson
import olyarisu.github.com.cocktailapp.data.dto.glass.GlassJson
import olyarisu.github.com.cocktailapp.data.firebase.FirebaseAuthRepository
import olyarisu.github.com.cocktailapp.data.firebase.FirestoreRepository
import olyarisu.github.com.cocktailapp.data.home.*
import olyarisu.github.com.cocktailapp.data.mapper.Mapper
import olyarisu.github.com.cocktailapp.data.network.NetworkService
import olyarisu.github.com.cocktailapp.data.random.DefaultRandomRepository
import olyarisu.github.com.cocktailapp.data.random.RandomDatasource
import olyarisu.github.com.cocktailapp.data.random.RemoteRandomDatasource
import olyarisu.github.com.cocktailapp.data.search.DefaultSearchRepository
import olyarisu.github.com.cocktailapp.data.search.RemoteSearchDatasource
import olyarisu.github.com.cocktailapp.data.search.SearchDatasource
import olyarisu.github.com.cocktailapp.data.search.SearchResultDataMapper
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailDetailsRepository
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.DefaultCocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.db.DatabaseRepository
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.favourite.DefaultFavouriteModel
import olyarisu.github.com.cocktailapp.domain.favourite.FavouriteModel
import olyarisu.github.com.cocktailapp.domain.home.DefaultHomeModel
import olyarisu.github.com.cocktailapp.domain.home.HomeModel
import olyarisu.github.com.cocktailapp.domain.home.HomeRepository
import olyarisu.github.com.cocktailapp.domain.login.AuthRepository
import olyarisu.github.com.cocktailapp.domain.login.DefaultLoginModel
import olyarisu.github.com.cocktailapp.domain.login.LoginModel
import olyarisu.github.com.cocktailapp.domain.random.DefaultRandomModel
import olyarisu.github.com.cocktailapp.domain.random.RandomModel
import olyarisu.github.com.cocktailapp.domain.random.RandomRepository
import olyarisu.github.com.cocktailapp.domain.search.DefaultSearchModel
import olyarisu.github.com.cocktailapp.domain.search.SearchModel
import olyarisu.github.com.cocktailapp.domain.search.SearchRepository
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsPresenter
import olyarisu.github.com.cocktailapp.presentation.favouritelist.FavouriteCocktailsPresenter
import olyarisu.github.com.cocktailapp.presentation.home.HomePresenter
import olyarisu.github.com.cocktailapp.presentation.login.LoginPresenter
import olyarisu.github.com.cocktailapp.presentation.random.RandomPresenter
import olyarisu.github.com.cocktailapp.presentation.search.SearchPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val applicationModule = module {

    single {
        NetworkService(
            androidContext().cacheDir,
            androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

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
    single(qualifier = StringQualifier("alcoholic")) { AlcoholicDataMapper() as Mapper<AlcoholicJson, List<Category>> }
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
    factory { SearchPresenter(get()) }
    single { DefaultSearchModel(get()) as SearchModel }
    single {
        DefaultSearchRepository(
            get(),
            get(StringQualifier("search"))
        ) as SearchRepository
    }
    single { RemoteSearchDatasource(get()) as SearchDatasource }
    single(qualifier = StringQualifier("search")) { SearchResultDataMapper() as Mapper<DrinkJson, List<Cocktail>> }

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

    //Favourite
    factory { FavouriteCocktailsPresenter(get()) }
    single {DefaultFavouriteModel(get(), get()) as FavouriteModel }
    single {FirebaseAuthRepository(FirebaseAuth.getInstance()) as AuthRepository}
    single { FirestoreRepository(FirebaseFirestore.getInstance()) as DatabaseRepository }

    //Login
    factory { LoginPresenter(get()) }
    single {DefaultLoginModel(get()) as LoginModel }
}
