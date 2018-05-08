package murmur.sunshine.data

import android.content.Context
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import murmur.sunshine.data.api.response.mapper.toEntity
import murmur.sunshine.data.db.AppDatabase
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.util.SingletonItem
import java.util.Date

class WeatherRepository(ctx: Context)  {
    private val networkService = SingletonItem.weatherService
    private val weatherDao = AppDatabase.getInstance(ctx).weatherDao()

    fun needFetchedNew(): Single<Boolean> {
        return Single.create<Boolean>{
            val count = weatherDao.countAllFutureWeather(Date().time)
            Log.d("kanna", "${Date().time} ${Date()} count is" +
                    " $count ${Thread.currentThread()}")
            it.onSuccess(count < 14)
        }
    }

    private fun resetDb(weatherList: List<WeatherEntry>) {
        weatherDao.clearAndInsert(weatherList)
    }

    fun fetchFromNet(): Completable {
        return networkService.getWeather()
                .map{
                    Log.d("kanna", "network success ${Thread.currentThread()}")
                    resetDb(it.list.toEntity())
                }.ignoreElement()
    }

    fun fetchFromDb(): Flowable<List<WeatherEntry>> {
        return weatherDao.getWeatherAll()
    }

    fun getWeatherDetail(id: Long): Flowable<WeatherEntry> {
        Log.d("kanna", "get detail $id")
        return weatherDao.getWeatherById(id)
    }
}