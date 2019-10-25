package olyarisu.github.com.cocktailapp.presentation.cocktaildetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cocktail_details.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient
import olyarisu.github.com.cocktailapp.presentation.adapter.IngredientsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import olyarisu.github.com.cocktailapp.COCKTAIL_ID
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf


class CocktailDetailsFragment : BaseFragment(), CocktailDetailsView {


    @InjectPresenter
    lateinit var presenter: CocktailDetailsPresenter

    override val layoutRes = R.layout.fragment_cocktail_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initResyclerView()
        button_favorite.setOnCheckedChangeListener { _, isChecked ->
            presenter.favouriteButtonPressed(isChecked)
        }
    }

    private fun initResyclerView(){
        list_ingredients.layoutManager = LinearLayoutManager(activity as Context)
        list_ingredients.addItemDecoration(
            DividerItemDecoration(
                activity as Context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun setName(name: String?) {
        title_cocktail.text = name
    }

    override fun setInstruction(instruction: String?) {
        description_cocktail.text = instruction
    }
    override fun setCategory(category: String) {
        category_cocktail.text = category
    }

    override fun setIngredients(ingredients: List<Ingredient>?) {
        list_ingredients.adapter =
            IngredientsAdapter(ingredients, activity as Context)
    }

    override fun setPhoto(photoURL: String?) {
        Glide.with(this)
            .load(photoURL)
            .into(image_cocktail)
    }

    override fun showContainer() {
        container.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun gotoLoginScreen(){
        (parentFragment as AppFragment).gotoScreen(LoginFragment())
    }

    override fun setFavButtonPressed() {
        button_favorite.isChecked = true
    }

    @ProvidePresenter
    fun providePresenter(): CocktailDetailsPresenter {
        //TODO FIX IT
        val id = arguments?.getInt(COCKTAIL_ID, 11006) ?: 11006
        return get { parametersOf(id) }
    }
}