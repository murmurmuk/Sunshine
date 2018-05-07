package murmur.sunshine.ui.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import murmur.sunshine.data.WeatherRepository


class DetailViewModelFactory(private val repository: WeatherRepository,
                             private val weatherId: Long)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailViewModel(repository, weatherId) as T
    }

}