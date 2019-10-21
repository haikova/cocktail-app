package olyarisu.github.com.cocktailapp.presentation.random

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.random.RandomModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsView

@InjectViewState
class RandomPresenter(
    val model: RandomModel
) : BasePresenter<RandomView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCocktailDetails()
    }

    //TODO fix dispose()
    fun loadCocktailDetails() =
        model.getRandomCocktail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showProgressBar()
                viewState.disableTryAgainButton()
            }
            .doFinally {
                viewState.hideProgress()
                viewState.enableTryAgainButton()
            }
            .subscribe(
                { cocktail -> showCocktailDetails(cocktail) },
                { error -> viewState.showError(error) })

    private fun showCocktailDetails(cocktail: Cocktail) {
        viewState.setName(cocktail.name)
        viewState.setInstruction(cocktail.instruction)
        viewState.setCategory(cocktail.category + ", " + cocktail.glass + ", " + cocktail.alcoholic)
        viewState.showContainer()
        viewState.setIngredients(cocktail.ingredients)
        viewState.setPhoto(cocktail.photoURL)
    }
}