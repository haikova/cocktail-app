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
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.search.SearchResultFragment


class HomeFragment : BaseFragment(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        //TODO fix this
        search_view.setOnClickListener { presenter.searchPressed() }
        text_search.setOnClickListener { presenter.searchPressed() }
    }

    private fun initRecyclerView() {
        list_categories.layoutManager =
            LinearLayoutManager(activity as Context, LinearLayout.HORIZONTAL, false)

        val listCategories = ArrayList<String>()
        for (i in 1..10) {
            listCategories.add("category $i")
        }

        LinearSnapHelper().attachToRecyclerView(list_categories)

        list_categories.adapter =
            CategoriesAdapter(listCategories, activity as Context)

    }

    override fun gotoSearchScreen() {
        parentFragment?.let {
            (it as AppFragment).gotoScreen(SearchResultFragment())
        }
    }
}