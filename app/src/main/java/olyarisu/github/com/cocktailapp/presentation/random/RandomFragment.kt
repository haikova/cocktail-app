package olyarisu.github.com.cocktailapp.presentation.random

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_random.*
import kotlinx.android.synthetic.main.fragment_random.button_favorite
import kotlinx.android.synthetic.main.fragment_random.category_cocktail
import kotlinx.android.synthetic.main.fragment_random.container
import kotlinx.android.synthetic.main.fragment_random.description_cocktail
import kotlinx.android.synthetic.main.fragment_random.image_cocktail
import kotlinx.android.synthetic.main.fragment_random.list_ingredients
import kotlinx.android.synthetic.main.fragment_random.progress_bar
import kotlinx.android.synthetic.main.fragment_random.title_cocktail
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient
import olyarisu.github.com.cocktailapp.presentation.adapter.IngredientsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment
import org.koin.android.ext.android.get

class RandomFragment : BaseFragment(), RandomView {

    @InjectPresenter
    lateinit var presenter: RandomPresenter

    override val layoutRes: Int = R.layout.fragment_random

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        button_tryagain.setOnClickListener { presenter.loadCocktailDetails() }
        button_favorite.setOnCheckedChangeListener { _, isChecked ->
            presenter.favouriteButtonPressed(isChecked)
        }
    }

    private fun initRecyclerView() {
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

    override fun enableTryAgainButton() {
        button_tryagain.visibility = View.VISIBLE
    }

    override fun disableTryAgainButton() {
        button_tryagain.visibility = View.GONE
    }

    override fun gotoLoginScreen() {
        (parentFragment as AppFragment).gotoScreen(LoginFragment())
    }

    @ProvidePresenter
    fun providePresenter(): RandomPresenter {
        return get()
    }
}