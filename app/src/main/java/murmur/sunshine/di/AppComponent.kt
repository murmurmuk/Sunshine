package murmur.sunshine.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import murmur.sunshine.di.detailactivity.DetailActivityBuilder
import murmur.sunshine.di.mainactivity.MainActivityBuilder
import murmur.sunshine.ui.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    MainActivityBuilder::class,
    DetailActivityBuilder::class
])
interface AppComponent : AndroidInjector<App>{
    override fun inject(instance: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(ctx: Context): Builder

        fun build(): AppComponent
    }
}