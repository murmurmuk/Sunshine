package murmur.sunshine.di.mainactivity

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import murmur.sunshine.di.ViewModelKey
import murmur.sunshine.ui.main.MainViewModel

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModell(mainViewModel: MainViewModel): ViewModel
}