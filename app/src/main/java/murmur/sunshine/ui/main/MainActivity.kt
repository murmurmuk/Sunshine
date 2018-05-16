package murmur.sunshine.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dagger.android.AndroidInjection
import murmur.sunshine.R
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.databinding.ActivityMainBinding
import murmur.sunshine.ui.detail.DetailActivity
import murmur.sunshine.ui.main.ForecastAdapter.ClickHandler
import java.util.Date
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ClickHandler {

    @Inject
    lateinit var factory: MainViewModelFactory

    override fun clickEvent(id: Long?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityMainBinding>(
                        this, R.layout.activity_main)

        val mainViewModel = ViewModelProviders
                .of(this, factory)[MainViewModel::class.java]

        mainViewModel.forecast.observe(this, Observer {
            it?.forEach {
               Log.d("kanna", "${it.date} ${Date(it.date)} ${it.description} ")
            }
            updateRecyclerView(binding, it)
        })
    }

    private fun updateRecyclerView(binding: ActivityMainBinding, list: List<WeatherEntry>?) {
        if (list != null) {
            binding.forecastRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.forecastRecyclerView.setHasFixedSize(true)
            binding.forecastRecyclerView.adapter = ForecastAdapter(list, this)
        }
    }
}
