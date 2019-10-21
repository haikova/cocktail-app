package olyarisu.github.com.cocktailapp.data.network

import android.os.Environment
import okhttp3.Cache
import okhttp3.OkHttpClient
import olyarisu.github.com.cocktailapp.MainApplication
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class NetworkService {

    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
/*    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(
        File(MainApplication.),
        cacheSize
    )
    private val okHttpClient = OkHttpClient.Builder()
        // Specify the cache we created earlier.
        .cache(myCache)
        // Add an Interceptor to the OkHttpClient.
        .addInterceptor { chain ->

            // Get the request from the chain.
            var request = chain.request()

            *//*
            *  Leveraging the advantage of using Kotlin,
            *  we initialize the request and change its header depending on whether
            *  the device is connected to Internet or not.
            *//*
            request = if (hasNetwork(context)!!)
            *//*
            *  If there is Internet, get the cache that was stored 5 seconds ago.
            *  If the cache is older than 5 seconds, then discard it,
            *  and indicate an error in fetching the response.
            *  The 'max-age' attribute is responsible for this behavior.
            *//*
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
            *//*
            *  If there is no Internet, get the cache that was stored 7 days ago.
            *  If the cache is older than 7 days, then discard it,
            *  and indicate an error in fetching the response.
            *  The 'max-stale' attribute is responsible for this behavior.
            *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
            *//*
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            // End of if-else statement

            // Add the modified request to the chain.
            chain.proceed(request)
        }
        .build()*/

    private fun getRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getCocktailService(): CocktailApi {
        return getRetrofitInstance().create(CocktailApi::class.java)
    }

}