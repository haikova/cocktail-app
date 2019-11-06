package olyarisu.github.com.cocktailapp.domain.login

class DefaultLoginModel(
    private val authRepository: AuthRepository
) : LoginModel {

    override fun authWithGoogle(idToken: String) = authRepository.authWithGoogle(idToken)
}