package murmur.sunshine.data

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import murmur.sunshine.data.api.response.WeatherResponse
import murmur.sunshine.data.api.response.mapper.toEntity
import murmur.sunshine.data.db.AppDatabase
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.util.SingletonItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import kotlin.concurrent.thread

class WeatherRepository(ctx: Context)  {
    private val networkService = SingletonItem.weatherService
    private val weatherDao = AppDatabase.getInstance(ctx).weatherDao()

    private fun needFetchedNew(): Boolean {
        val count = weatherDao.countAllFutureWeather(Date().time)
        Log.d("kanna", Date().time.toString() + " " +
                Date().toString() + " count is" + count.toString())
        return count < 14
    }

    private fun resetDb(weatherList: List<WeatherEntry>) {
        thread{
            weatherDao.clearAndInsert(weatherList)
        }
    }

    private fun fetchFromNet(liveData: MutableLiveData<List<WeatherEntry>>) {
        Log.d("kanna", "fetch from network")
        val call = networkService.getWeather()

        call.enqueue(object: Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.d("kanna", "failure ${t.toString()}")
            }

            override fun onResponse(call: Call<WeatherResponse>?,
                                    response: Response<WeatherResponse>?) {
                Log.d("kanna", "success ${response?.body().toString()}")
                val weatherList = response?.body()?.list.toEntity()
                liveData.postValue(weatherList)
                resetDb(weatherList)
            }

        })
    }

    private fun fetchFromCache(liveData: MutableLiveData<List<WeatherEntry>>) {
        Log.d("kanna", "fetch from db")
        liveData.postValue(weatherDao.getWeatherAll())
    }


    fun getForecast(liveData: MutableLiveData<List<WeatherEntry>>) {
        thread {
            if (needFetchedNew()) {
                fetchFromNet(liveData)
            } else {
                fetchFromCache(liveData)
            }
        }
    }

    fun getWeatherDetail(liveData: MutableLiveData<WeatherEntry>, id: Long) {
        Log.d("kanna", "get detail $id")
        thread {
            liveData.postValue(weatherDao.getWeatherById(id))
        }
    }
}