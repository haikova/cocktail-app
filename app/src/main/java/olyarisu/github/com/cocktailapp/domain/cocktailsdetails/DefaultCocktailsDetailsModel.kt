package olyarisu.github.com.cocktailapp.domain.cocktailsdetails


class DefaultCocktailsDetailsModel(
    private val cocktailDetailsRepository: CocktailDetailsRepository
) : CocktailsDetailsModel{
    override fun checkUserLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCocktailDetails(id: Int) =
        cocktailDetailsRepository.getRemoteCocktailDetails(id)
}