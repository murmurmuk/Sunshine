package murmur.sunshine.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import murmur.sunshine.ui.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent {
    fun inject(instance: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(ctx: Context): Builder

        fun build(): AppComponent
    }
}