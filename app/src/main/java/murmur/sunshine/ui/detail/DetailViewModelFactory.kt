package murmur.sunshine.ui.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import murmur.sunshine.data.WeatherRepository
import javax.inject.Inject


class DetailViewModelFactory @Inject constructor(private val repository: WeatherRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailViewModel(repository) as T
    }

}