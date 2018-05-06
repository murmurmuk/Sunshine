package murmur.sunshine.data.api

import murmur.sunshine.data.api.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {
    @GET("weather")
    fun getWeather(): Call<WeatherResponse>
}
