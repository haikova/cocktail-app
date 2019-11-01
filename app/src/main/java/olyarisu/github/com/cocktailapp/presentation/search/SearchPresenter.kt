package olyarisu.github.com.cocktailapp.presentation.search

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.search.SearchModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class SearchPresenter(
    private val model: SearchModel
) : BasePresenter<SearchView>() {

    fun searchPressed(searchText: String) =
        model.searchCocktail(searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .doFinally {
                viewState.hideProgress()
                viewState.hideKeyboard()
            }
            .subscribe(
                { cocktails -> viewState.showSearchResult(cocktails) },
                { error -> viewState.showError(error) })
}
