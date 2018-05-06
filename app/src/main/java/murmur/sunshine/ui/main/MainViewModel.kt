package murmur.sunshine.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry

class MainViewModel (private val repository: WeatherRepository) : ViewModel() {
    fun getForecast(): MutableLiveData<List<WeatherEntry>>{
        val list = MutableLiveData<List<WeatherEntry>>()
        repository.getForecast(list)
        return list
    }
}