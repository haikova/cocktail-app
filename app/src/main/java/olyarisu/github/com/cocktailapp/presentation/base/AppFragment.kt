package olyarisu.github.com.cocktailapp.presentation.base

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_app.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.presentation.CocktailDetailsFragment
import olyarisu.github.com.cocktailapp.presentation.FavouriteCocktalsFragment
import olyarisu.github.com.cocktailapp.presentation.HomeFragment


class AppFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_app

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment()).commit()

        initBottomBar()
    }

    private fun initBottomBar() {
        bottom_bar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_item -> gotoHomeTab()
                R.id.favourite_item -> gotoFavouritesTab()
                R.id.shopping_item -> gotoShoppingList()
                else -> {}
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun gotoHomeTab() = gotoTab(HomeFragment())
    private fun gotoFavouritesTab() = gotoTab(FavouriteCocktalsFragment())
    private fun gotoShoppingList() = gotoTab(CocktailDetailsFragment())

    private fun gotoTab(fragment: BaseFragment) {
        childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}