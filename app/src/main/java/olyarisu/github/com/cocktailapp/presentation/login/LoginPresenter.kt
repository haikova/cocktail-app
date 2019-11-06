package olyarisu.github.com.cocktailapp.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import olyarisu.github.com.cocktailapp.domain.login.LoginModel
import olyarisu.github.com.cocktailapp.presentation.base.BasePresenter

@InjectViewState
class LoginPresenter(
    private val model : LoginModel
): BasePresenter<LoginView>() {

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.idToken?.let {
                model.authWithGoogle(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { viewState.finishScreen() }
            }
        } catch (e: ApiException) {
            viewState.showError(e)
        }
    }
}
