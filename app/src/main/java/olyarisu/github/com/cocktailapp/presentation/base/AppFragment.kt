package olyarisu.github.com.cocktailapp.presentation.base

import android.os.Bundle
import android.view.Menu
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
        transaction.replace(
            R.id.fragment_container,
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
                else -> {
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun gotoHomeTab() = gotoTab(HomeFragment())
    private fun gotoFavouritesTab() = gotoTab(FavouriteCocktalsFragment())
    private fun gotoShoppingList() = gotoTab(CocktailDetailsFragment())

    fun gotoTab(fragment: BaseFragment) =
        childFragmentManager.beginTransaction().addToBackStack(null).replace(
            R.id.fragment_container,
            fragment
        ).commit()

    fun gotoScreen(fragment: BaseFragment) {
        childFragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.fragment_container, fragment).commit()
    }

    fun gotoScreen(fragment: BaseFragment, bundle: Bundle) {
        fragment.arguments = bundle
        childFragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.fragment_container, fragment).commit()
    }

    private fun highlitTab(tabRes: Int) {
        val menu = bottom_bar.menu
        menu.getItem(tabRes).isChecked = true
    }


    //TODO fix back navigation https://medium.com/@smihajlovskih/create-instagram-like-backstack-4711600c5bff
    override fun onBackPressed(): Boolean? {
        val count = childFragmentManager.backStackEntryCount
        bottom_bar.selectedItemId

        return if (count == 0) {
            super.onBackPressed()
        } else {
            childFragmentManager.popBackStack()
            true
        }
    }
}