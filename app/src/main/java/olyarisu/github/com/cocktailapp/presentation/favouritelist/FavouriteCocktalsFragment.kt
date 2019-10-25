package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import olyarisu.github.com.cocktailapp.COCKTAIL_ID
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList
import olyarisu.github.com.cocktailapp.presentation.adapter.FavouriteCocktailAdapter
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsFragment
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment


class FavouriteCocktalsFragment : BaseFragment(), FavouriteCocktalsView {
    override fun showTip() {
        Toast.makeText(activity, "Add some cocktails to favorites", Toast.LENGTH_LONG).show()
    }

    override fun showFavouriteCocktails(list: FavouriteList) {
        initRecyclerView()
        rv_favourites.adapter = FavouriteCocktailAdapter(list, activity as Context){
            gotoCocktailDetials(it.id)
        }
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
    lateinit var presenter: FavouriteCocktalsPresenter

    override val layoutRes = R.layout.fragment_favourites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_favourites.layoutManager = LinearLayoutManager(activity as Context)
    }

    override fun gotoLoginScreen(){
        (parentFragment as AppFragment).gotoScreen(LoginFragment())
    }
}