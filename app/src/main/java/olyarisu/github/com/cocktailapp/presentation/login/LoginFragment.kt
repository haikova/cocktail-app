package olyarisu.github.com.cocktailapp.presentation.login

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.get


class LoginFragment : BaseFragment(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    private val rcSigIn = 13
    private lateinit var auth: FirebaseAuth

    override val layoutRes: Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        button_login.setOnClickListener {
            sigIn()
        }
    }

    private fun sigIn() {
        activity?.let {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val signInIntent = GoogleSignIn.getClient(it, gso).signInIntent
            startActivityForResult(signInIntent, rcSigIn)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == rcSigIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            presenter.handleSignInResult(task)
        }
    }

    override fun finishScreen() {
        fragmentManager?.popBackStack()
    }

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        return get()
    }
}