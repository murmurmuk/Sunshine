package murmur.sunshine

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.databinding.ActivityDetailBinding
import murmur.sunshine.detail.DetailViewModel
import java.lang.Thread.sleep
import java.util.Date
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityDetailBinding>(
                        this, R.layout.activity_detail)
        val detailViewModel = ViewModelProviders
                .of(this)[DetailViewModel::class.java]

        detailViewModel.weather.observe(this, Observer<WeatherEntry> {
            binding.weather = it
        })

        test(detailViewModel)
    }

    private fun test(detailViewModel: DetailViewModel) {
        thread {
            try {
                sleep(4000)
                detailViewModel.weather.postValue(WeatherEntry(null, 123,
                        Date(), 1.0, 2.0, 0.0, 0.0,0.0, 33.0))
                sleep(4000)
                detailViewModel.weather.postValue(WeatherEntry(null, 5566,
                        Date(), 1.0, 2.0, 0.0, 0.0,0.0, 56.0))

            } catch(e: InterruptedException) {

            }
        }
    }
}
