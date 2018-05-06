package murmur.sunshine.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import murmur.sunshine.R
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityMainBinding>(
                        this, R.layout.activity_main)

        val factory = MainViewModelFactory(
                WeatherRepository(this.applicationContext))

        val mainViewModel = ViewModelProviders
                .of(this, factory)[MainViewModel::class.java]

        mainViewModel.getForecast().observe(this, Observer {
            it?.forEach {
               Log.d("kanna", "${it.date} ${Date(it.date)} ${it.description} ")
            }
        })
    }
}
