package olyarisu.github.com.cocktailapp.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_search_result.*
import kotlinx.android.synthetic.main.fragment_search_result.progress_bar
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail
import olyarisu.github.com.cocktailapp.presentation.adapter.CocktailsAdapter
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import org.koin.android.ext.android.get
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import olyarisu.github.com.cocktailapp.COCKTAIL_ID
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.cocktaildetails.CocktailDetailsFragment


class SearchFragment : BaseFragment(),
    SearchView {
    override val layoutRes: Int = R.layout.fragment_search_result

    @InjectPresenter
    lateinit var presenter : SearchPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        text_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.searchPressed(text_search.text.toString())
                    return true
                }
                return false
            }
        })
        button_search.setOnClickListener { presenter.searchPressed(text_search.text.toString()) }
        text_search.requestFocus()
        showKeyboard()
    }

    override fun showSearchResult(searchResult: List<Cocktail>){
        (list_search_result.adapter as CocktailsAdapter).replace(searchResult)
    }

    private fun initRecyclerView(){
        list_search_result.layoutManager = LinearLayoutManager(activity as Context)
        list_search_result.addItemDecoration(
            DividerItemDecoration(
                activity as Context,
                DividerItemDecoration.VERTICAL
            )
        )
        list_search_result.adapter =
            CocktailsAdapter(arrayListOf(), activity as Context) {
                gotoCocktailDetails(it.id)
            }
    }

    private fun gotoCocktailDetails(id: Int?) {
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

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private fun showKeyboard(){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(text_search, InputMethodManager.SHOW_IMPLICIT)
    }
    override fun hideKeyboard(){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(text_search.windowToken, 0)
    }

    @ProvidePresenter
    fun providePresenter(): SearchPresenter {
        return get()
    }

}