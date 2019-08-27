package olyarisu.github.com.cocktailapp.presentation.favouritelist

import android.content.Context
import android.os.Bundle
import android.view.View
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.adapter.CocktailsAdapter


class FavouriteCocktalsFragment : BaseFragment(), FavouriteCocktalsView {

    @InjectPresenter
    lateinit var presenter: FavouriteCocktalsPresenter

    override val layoutRes = R.layout.fragment_favourites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_favourites.layoutManager = LinearLayoutManager(activity as Context)

        val listCocktails = ArrayList<Cocktail>()
        for (i in 1..20) {
            listCocktails.add(Cocktail(
                id = 1,
                name = "name $i",
                instruction = null,
                ingredients = null,
                alcoholic = "alcoholic",
                category = "category",
                glass = "glass"
            ))
        }

        rv_favourites.adapter = CocktailsAdapter(listCocktails, activity as Context)
    }
}