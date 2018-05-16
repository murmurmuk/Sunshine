package murmur.sunshine.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import murmur.sunshine.ui.detail.DetailActivity
import murmur.sunshine.ui.main.MainActivity

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeDetailActivity(): DetailActivity
}