package olyarisu.github.com.cocktailapp.presentation.favouritelist

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.data.EmptyDBDocumentException
import olyarisu.github.com.cocktailapp.data.LoginException
import olyarisu.github.com.cocktailapp.domain.favourite.FavouriteModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class FavouriteCocktailsPresenter(
    private val model: FavouriteModel
) : BasePresenter<FavouriteCocktalsView>() {

    override fun attachView(view: FavouriteCocktalsView?) {
        super.attachView(view)
        loadFavouriteCocktails()
    }

    private fun loadFavouriteCocktails() =
        model.getFavouriteCocktails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe(
                { cocktailsList -> viewState.showFavouriteCocktails(cocktailsList) },
                { error ->
                    when (error) {
                        is LoginException -> viewState.gotoLoginScreen()
                        is EmptyDBDocumentException -> viewState.showTip()
                        else -> viewState.showError(error)
                    }
                })
}