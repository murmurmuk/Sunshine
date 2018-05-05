package murmur.sunshine.data.api.Response

data class WeatherResponse (val list: List<WeatherItem>)

data class WeatherItem (val dt: Long,
                        val temp: Temperature,
                        val humidity: Double,
                        val weather: List<WeatherDescription>)

data class Temperature(val min: Double,
                       val max: Double)

data class WeatherDescription(val id: Int,
                              val description: String)