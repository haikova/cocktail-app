package olyarisu.github.com.cocktailapp.presentation.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment

abstract  class BaseFragment: MvpAppCompatFragment(), BaseView {
    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(layoutRes, container, false)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
        Log.e("COCKTAIL-APP", "error", throwable)
    }
}