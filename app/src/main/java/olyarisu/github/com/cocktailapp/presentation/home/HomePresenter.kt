package olyarisu.github.com.cocktailapp.presentation.home

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.home.HomeModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class HomePresenter(
    //val model: HomeModel
) : BasePresenter<HomeView>() {

    fun searchPressed() {
        viewState.gotoSearchScreen()
    }
}