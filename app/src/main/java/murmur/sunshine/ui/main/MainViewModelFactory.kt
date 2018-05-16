package murmur.sunshine.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import murmur.sunshine.data.WeatherRepository
import javax.inject.Inject


class MainViewModelFactory @Inject constructor(private val repository: WeatherRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(repository) as T
    }

}