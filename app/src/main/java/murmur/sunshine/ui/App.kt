package murmur.sunshine.ui

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import murmur.sunshine.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .appContext(this)
                .build()
    }
}