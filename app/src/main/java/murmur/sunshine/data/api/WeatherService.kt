package murmur.sunshine.data.api

import murmur.sunshine.data.api.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherService {
    @GET("weather")
    fun getWeather(): Single<WeatherResponse>
}
