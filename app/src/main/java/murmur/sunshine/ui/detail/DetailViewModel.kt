package murmur.sunshine.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import murmur.sunshine.data.db.entity.WeatherEntry

class DetailViewModel : ViewModel() {
    val weather = MutableLiveData<WeatherEntry>()
}