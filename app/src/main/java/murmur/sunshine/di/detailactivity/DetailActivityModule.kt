package murmur.sunshine.di.detailactivity

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import murmur.sunshine.di.ViewModelKey
import murmur.sunshine.ui.detail.DetailViewModel

@Module
interface DetailActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}