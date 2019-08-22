package olyarisu.github.com.cocktailapp.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private fun getRetrofitInstance() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getCocktailService(): CocktailApi {
        return getRetrofitInstance().create(CocktailApi::class.java)
    }

}