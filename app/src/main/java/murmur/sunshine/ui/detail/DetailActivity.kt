package murmur.sunshine.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import murmur.sunshine.R
import murmur.sunshine.data.WeatherRepository
import murmur.sunshine.databinding.ActivityDetailBinding
import murmur.sunshine.util.WeatherEntryUtil

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityDetailBinding>(
                        this, R.layout.activity_detail)
        val weatherId = intent.getLongExtra("id", 0L)
        val factory = DetailViewModelFactory(
                WeatherRepository(this.applicationContext), weatherId)
        val detailViewModel = ViewModelProviders
                .of(this, factory)[DetailViewModel::class.java]
        detailViewModel.weatherDetail.observe(this, Observer {
            Log.d("kanna", "$it")
            binding.entry = it
            binding.util = WeatherEntryUtil
            binding.weatherIcon
                    .setImageResource(WeatherEntryUtil.getWeatherPic(it!!.weatherIconId))
        })
    }
}