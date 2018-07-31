package murmur.sunshine.di.mainactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import murmur.sunshine.ui.main.MainActivity

@Module
interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}