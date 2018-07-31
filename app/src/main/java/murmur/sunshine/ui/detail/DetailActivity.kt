package murmur.sunshine.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import murmur.sunshine.R
import murmur.sunshine.databinding.ActivityDetailBinding
import murmur.sunshine.util.WeatherEntryUtil
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityDetailBinding>(
                        this, R.layout.activity_detail)
        val weatherId = intent.getLongExtra("id", 0L)

        val detailViewModel = ViewModelProviders
                .of(this, factory)[DetailViewModel::class.java]
        detailViewModel.getWeatherDetail(weatherId).observe(this, Observer {
            Log.d("kanna", "$it")
            binding.entry = it
            binding.util = WeatherEntryUtil
            binding.weatherIcon
                    .setImageResource(WeatherEntryUtil.getWeatherPic(it!!.weatherIconId))
        })
    }
}