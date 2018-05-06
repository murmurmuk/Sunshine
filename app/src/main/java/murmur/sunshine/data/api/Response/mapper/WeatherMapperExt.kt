package murmur.sunshine.data.api.response.mapper

import murmur.sunshine.data.api.response.WeatherItem
import murmur.sunshine.data.db.entity.WeatherEntry
import java.util.concurrent.TimeUnit

fun List<WeatherItem>?.toEntity(): List<WeatherEntry> =
    this!!.map{
        WeatherEntry(null,
                it.weather[0].id,
                TimeUnit.MILLISECONDS.convert(it.dt, TimeUnit.SECONDS),
                it.temp.min,
                it.temp.max,
                it.humidity,
                it.weather[0].description)
    }


