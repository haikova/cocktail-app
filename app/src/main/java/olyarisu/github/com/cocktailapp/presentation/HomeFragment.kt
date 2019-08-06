package olyarisu.github.com.cocktailapp.presentation

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


class HomeFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
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
}