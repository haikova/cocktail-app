package olyarisu.github.com.cocktailapp.presentation.home

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.home.HomeModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class HomePresenter(
    private val model: HomeModel
) : BasePresenter<HomeView>() {

    override fun attachView(view: HomeView?) {
        super.attachView(view)
        loadCategories()
    }

    fun searchPressed() {
        viewState.gotoSearchScreen()
    }

    private fun loadCategories() =
        model.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe(
                { categories -> viewState.showCategories(categories) },
                { error -> viewState.showError(error) })

    fun loadCocktailListByCategory(category: Category) =
        model.getCocktailsListByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe(
                { cocktails ->
                    viewState.hideTip()
                    viewState.showCocktailList(cocktails)
                    viewState.setCocktailsListTitle(category.name)
                },
                { error -> viewState.showError(error) })
}