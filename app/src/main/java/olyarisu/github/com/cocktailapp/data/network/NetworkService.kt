package olyarisu.github.com.cocktailapp.data.network

import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class NetworkService(
    cacheDir: File,
    private val connectivityManager: ConnectivityManager
) {

    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(
        cacheDir,
        cacheSize
    )

    private fun hasNetwork(): Boolean? {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork()!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()

    private fun getRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    fun getCocktailService(): CocktailApi {
        return getRetrofitInstance().create(CocktailApi::class.java)
    }

}