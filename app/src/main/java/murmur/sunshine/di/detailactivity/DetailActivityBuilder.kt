package murmur.sunshine.di.detailactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import murmur.sunshine.ui.detail.DetailActivity

@Module
interface DetailActivityBuilder {
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    fun contributeDetailActivity(): DetailActivity
}