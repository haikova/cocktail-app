package olyarisu.github.com.cocktailapp.presentation.cocktaildetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cocktail_details.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient
import olyarisu.github.com.cocktailapp.presentation.adapter.FavouritesCocktailsAdapter
import olyarisu.github.com.cocktailapp.presentation.adapter.IngredientsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.arellomobile.mvp.presenter.InjectPresenter


class CocktailDetailsFragment : BaseFragment() {

    @InjectPresenter
    lateinit var presenter: CocktailDetailsPresenter

    override val layoutRes = R.layout.fragment_cocktail_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        list_ingredients.layoutManager = LinearLayoutManager(activity as Context)

        val listIngredients = ArrayList<Ingredient>()
        for (i in 1..5) {
            listIngredients.add(
                Ingredient(
                    id = 1,
                    name = "name $i",
                    description = ""
                )
            )
        }

        val listMeasures = ArrayList<String>()
        for (i in 1..5) {
            listMeasures.add("$i oz")
        }

        list_ingredients.adapter =
            IngredientsAdapter(listIngredients, listMeasures, activity as Context)
        list_ingredients.addItemDecoration(
            DividerItemDecoration(
                activity as Context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}