package murmur.sunshine.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry

class MainViewModel (private val repository: WeatherRepository) : ViewModel() {
    val forecast: LiveData<List<WeatherEntry>> by lazy {
        repository.getForecast()
    }
}