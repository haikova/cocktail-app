package olyarisu.github.com.cocktailapp.presentation.cocktaildetails

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.cocktailsdetails.CocktailsDetailsModel
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class CocktailDetailsPresenter(
    val model: CocktailsDetailsModel,
    val id: Int
) : BasePresenter<CocktailDetailsView>() {

    override fun attachView(view: CocktailDetailsView?) {
        super.attachView(view)
        loadCocktailDetails()
    }

    //TODO fix dispose()
    private fun loadCocktailDetails() =
        model.getCocktailDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .doFinally { viewState.hideProgress() }
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