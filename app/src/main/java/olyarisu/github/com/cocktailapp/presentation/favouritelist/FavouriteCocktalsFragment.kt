package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.adapter.CocktailsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment


class FavouriteCocktalsFragment : BaseFragment(), FavouriteCocktalsView {
    override fun showTip() {
        Toast.makeText(activity, "Add some cocktails to favorites", Toast.LENGTH_LONG).show()
    }

    override fun showFavouriteCocktails(list: MutableList<Cocktail>) {
        initRecyclerView()
        rv_favourites.adapter = CocktailsAdapter(list as ArrayList<Cocktail>, activity as Context)
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