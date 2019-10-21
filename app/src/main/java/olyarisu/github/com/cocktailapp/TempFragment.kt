package olyarisu.github.com.cocktailapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import olyarisu.github.com.cocktailapp.presentation.base.AppFragment
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import olyarisu.github.com.cocktailapp.presentation.login.LoginFragment

class TempFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_temp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let{
            Toast.makeText(activity, it.displayName, Toast.LENGTH_LONG).show()
        } ?: gotoLoginScreen()

    }

    private fun gotoLoginScreen(){
        (parentFragment as AppFragment).gotoScreen(LoginFragment())
    }
}