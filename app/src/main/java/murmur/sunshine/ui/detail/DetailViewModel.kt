package murmur.sunshine.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.util.toLiveData
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    private var detailLiveData: LiveData<WeatherEntry>? = null

    fun getWeatherDetail(id: Long): LiveData<WeatherEntry> {
        if (detailLiveData == null) {
            detailLiveData = repository.getWeatherDetail(id).toLiveData()
        }
        return detailLiveData!!
    }
}