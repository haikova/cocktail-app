package olyarisu.github.com.cocktailapp.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.presentation.adapter.CategoriesAdapter
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import androidx.recyclerview.widget.LinearSnapHelper
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import olyarisu.github.com.cocktailapp.COCKTAIL_ID
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.adapter.CocktailsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsFragment
import olyarisu.github.com.cocktailapp.presentation.search.SearchResultFragment
import org.koin.android.ext.android.get


class HomeFragment : BaseFragment(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategoryRecyclerView()

        //TODO fix this
        search_view.setOnClickListener { presenter.searchPressed() }
        text_search.setOnClickListener { presenter.searchPressed() }
    }

    override fun showCategories(listCategory: List<Category>) {
        (list_categories.adapter as CategoriesAdapter).add(listCategory)
    }

    override fun showCocktailList(cocktails: List<Cocktail>) {
        list_cocktails.layoutManager = LinearLayoutManager(activity as Context)
        list_cocktails.adapter =
            CocktailsAdapter(cocktails as ArrayList<Cocktail>, activity as Context) {
                gotoCocktailDetials(it.id)
            }
    }

    override fun setCocktailsListTitle(title: String) {
        title_cocktails_list.text = title
    }

    override fun showProgress(){
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress(){
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

    private fun initCategoryRecyclerView() {
        list_categories.layoutManager =
            LinearLayoutManager(activity as Context, LinearLayout.HORIZONTAL, false)
        list_categories.adapter =
            CategoriesAdapter(arrayListOf(), activity as Context) {
                presenter.loadCocktailListByCategory(it)
            }
    }

    override fun gotoSearchScreen() {
        parentFragment?.let {
            (it as AppFragment).gotoScreen(SearchResultFragment())
        }
    }

    @ProvidePresenter
    fun providePresenter(): HomePresenter {
        return get()
    }
}