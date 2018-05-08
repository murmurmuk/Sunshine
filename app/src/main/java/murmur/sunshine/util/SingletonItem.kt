package murmur.sunshine.util

import murmur.sunshine.data.api.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object SingletonItem {
    private val client = OkHttpClient()
    val weatherService = Retrofit.Builder()
            .client(client)
            .baseUrl("https://andfun-weather.udacity.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(WeatherService::class.java)!!
}

