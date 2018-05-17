package murmur.sunshine.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import murmur.sunshine.data.api.WeatherService
import murmur.sunshine.data.db.AppDatabase
import murmur.sunshine.data.db.AppDatabase.Companion.DATABASE_NAME
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppDatabase(ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx.applicationContext,
                AppDatabase::class.java, DATABASE_NAME).build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideWeatherService(client: OkHttpClient): WeatherService {
        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://andfun-weather.udacity.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(WeatherService::class.java)!!
    }
}