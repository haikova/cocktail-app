package olyarisu.github.com.cocktailapp.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

open class BasePresenter<V : BaseView> : MvpPresenter<V>() {
}