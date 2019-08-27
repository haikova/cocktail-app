package olyarisu.github.com.cocktailapp.domain.search

class DefaultSearchResultModel(
    private val searchResultModelRepository: SearchResultModelRepository
) : SearchResultModel {

    override fun searchCocktail(searchText: String) =
        searchResultModelRepository.serchCockatails(searchText)
}