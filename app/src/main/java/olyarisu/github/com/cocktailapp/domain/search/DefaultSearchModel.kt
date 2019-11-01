package olyarisu.github.com.cocktailapp.domain.search

class DefaultSearchModel(
    private val searchResultModelRepository: SearchRepository
) : SearchModel {

    override fun searchCocktail(searchText: String) =
        searchResultModelRepository.searchCocktails(searchText)
}