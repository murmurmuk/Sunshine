package murmur.sunshine

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import murmur.sunshine.data.api.Response.WeatherResponse
import murmur.sunshine.data.api.WeatherService
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.databinding.ActivityDetailBinding
import murmur.sunshine.detail.DetailViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Thread.sleep
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityDetailBinding>(
                        this, R.layout.activity_detail)
        val detailViewModel = ViewModelProviders
                .of(this)[DetailViewModel::class.java]

        detailViewModel.weather.observe(this, Observer<WeatherEntry> {
            binding.weather = it
        })

        //test(detailViewModel)
        testApi()
    }

    private fun testApi() {
        val retrofit = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl("https://andfun-weather.udacity.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getWeather()
        call.enqueue(object: Callback<WeatherResponse>{
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.d("kanna", "failure ${t.toString()}")
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                Log.d("kanna", "success ${response?.body().toString()}")
                val weatherList = response?.body()?.list
                weatherList?.forEach{
                    Log.d("kanna", "${it.dt} date =" +
                            " ${Date(TimeUnit.MILLISECONDS.convert(it.dt, TimeUnit.SECONDS))}" +
                            " ${it.weather[0].description} " +
                            " higest temp = ${it.temp.max}")
                }
            }

        })
    }

    private fun test(detailViewModel: DetailViewModel) {
        thread {
            try {
                sleep(4000)
                detailViewModel.weather.postValue(WeatherEntry(null, 123,
                        Date(), 1.0, 2.0, 0.0, "kanna"))
                sleep(4000)
                detailViewModel.weather.postValue(WeatherEntry(null, 5566,
                        Date(), 1.0, 2.0, 0.0, "chino"))

            } catch(e: InterruptedException) {

            }
        }
    }
}
