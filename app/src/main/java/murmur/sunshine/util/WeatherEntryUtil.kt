package murmur.sunshine.util

import murmur.sunshine.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object WeatherEntryUtil {

    fun getWeatherIcon(weatherId: Int): Int {
        if (weatherId in 200..232) {
            return R.drawable.ic_storm
        } else if (weatherId in 300..321) {
            return R.drawable.ic_light_rain
        } else if (weatherId in 500..504) {
            return R.drawable.ic_rain
        } else if (weatherId == 511) {
            return R.drawable.ic_snow
        } else if (weatherId in 520..531) {
            return R.drawable.ic_rain
        } else if (weatherId in 600..622) {
            return R.drawable.ic_snow
        } else if (weatherId in 701..761) {
            return R.drawable.ic_fog
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.ic_storm
        } else if (weatherId == 800) {
            return R.drawable.ic_clear
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds
        } else if (weatherId in 802..804) {
            return R.drawable.ic_cloudy
        } else if (weatherId in 900..906) {
            return R.drawable.ic_storm
        } else if (weatherId in 958..962) {
            return R.drawable.ic_storm
        } else if (weatherId in 951..957) {
            return R.drawable.ic_clear
        }
        return R.drawable.ic_storm
    }

    fun getWeatherPic(weatherId: Int): Int {
        if (weatherId in 200..232) {
            return R.drawable.art_storm
        } else if (weatherId in 300..321) {
            return R.drawable.art_light_rain
        } else if (weatherId in 500..504) {
            return R.drawable.art_rain
        } else if (weatherId == 511) {
            return R.drawable.art_snow
        } else if (weatherId in 520..531) {
            return R.drawable.art_rain
        } else if (weatherId in 600..622) {
            return R.drawable.art_snow
        } else if (weatherId in 701..761) {
            return R.drawable.art_fog
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.art_storm
        } else if (weatherId == 800) {
            return R.drawable.art_clear
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds
        } else if (weatherId in 802..804) {
            return R.drawable.art_clouds
        } else if (weatherId in 900..906) {
            return R.drawable.art_storm
        } else if (weatherId in 958..962) {
            return R.drawable.art_storm
        } else if (weatherId in 951..957) {
            return R.drawable.art_clear
        }
        return R.drawable.art_storm
    }

    fun getDate(timestamp: Long): String {
        val sdFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
        return sdFormat.format(Date(timestamp))
    }

    fun getTemperature(temperature: Double): String {
        //val deFormat = DecimalFormat("#.##")
        //return deFormat.format(temperature)
        return temperature.roundToInt().toString()
    }

    fun getHumidity(humidity: Double): String {
        val deFormat = DecimalFormat("#.##")
        return deFormat.format(humidity)
    }
}