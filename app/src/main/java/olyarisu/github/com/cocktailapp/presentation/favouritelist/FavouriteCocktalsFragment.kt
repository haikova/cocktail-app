package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.content.Context
import android.os.Bundle
import android.view.View
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_favourites.progress_bar
import olyarisu.github.com.cocktailapp.COCKTAIL_ID
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
import olyarisu.github.com.cocktailapp.presentation.adapter.FavouriteCocktailAdapter
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsFragment
import olyarisu.github.com.cocktailapp.presentation.home.HomePresenter
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment
import org.koin.android.ext.android.get


class FavouriteCocktalsFragment : BaseFragment(), FavouriteCocktalsView {

    override val layoutRes = R.layout.fragment_favourites

    override fun showTip() {
        fav_tip.visibility = View.VISIBLE
        rv_favourites.visibility = View.GONE
    }

    override fun showFavouriteCocktails(list: FavouriteList) {
        rv_favourites.layoutManager = LinearLayoutManager(activity as Context)
        rv_favourites.adapter = FavouriteCocktailAdapter(list, activity as Context) {
            gotoCocktailDetials(it.id)
        }
    }

    override fun gotoLoginScreen() {
        (parentFragment as AppFragment).gotoScreen(LoginFragment())
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private fun gotoCocktailDetials(id: Int?) {
        val bundle = Bundle()
        bundle.apply {
            if (id != null) {
                putInt(COCKTAIL_ID, id)
            }
        }
        parentFragment?.let {
            (it as AppFragment).gotoScreen(CocktailDetailsFragment(), bundle)
        }
    }

    @InjectPresenter
    lateinit var presenter: FavouriteCocktailsPresenter

    @ProvidePresenter
    fun providePresenter(): FavouriteCocktailsPresenter = get()
}