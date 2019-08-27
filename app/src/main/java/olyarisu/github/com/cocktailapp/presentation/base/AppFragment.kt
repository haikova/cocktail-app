package olyarisu.github.com.cocktailapp.presentation.base

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_app.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsFragment
import olyarisu.github.com.cocktailapp.presentation.favouritelist.FavouriteCocktalsFragment
import olyarisu.github.com.cocktailapp.presentation.home.HomeFragment


class AppFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_app

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,
            HomeFragment()
        ).commit()

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

    private fun gotoHomeTab() = gotoScreen(HomeFragment())
    private fun gotoFavouritesTab() = gotoScreen(FavouriteCocktalsFragment())
    private fun gotoShoppingList() = gotoScreen(CocktailDetailsFragment())

    fun gotoScreen(fragment: BaseFragment) {
        childFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment).commit()
    }

     fun gotoScreen(fragment: BaseFragment, bundle: Bundle) {
        fragment.arguments = bundle
        childFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragment_container, fragment).commit()
    }
}