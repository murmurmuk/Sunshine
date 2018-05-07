package murmur.sunshine.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry

class DetailViewModel(private val repository: WeatherRepository,
                      val id: Long) : ViewModel() {
    val weatherDetail: MutableLiveData<WeatherEntry> by lazy {
        val entry = MutableLiveData<WeatherEntry>()
        repository.getWeatherDetail(entry, id)
        entry
    }
}