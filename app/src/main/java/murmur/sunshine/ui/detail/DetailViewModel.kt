package murmur.sunshine.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry

class DetailViewModel(private val repository: WeatherRepository,
                      val id: Long) : ViewModel() {
    val weatherDetail: LiveData<WeatherEntry> by lazy {
        repository.getWeatherDetail(id)
    }
}