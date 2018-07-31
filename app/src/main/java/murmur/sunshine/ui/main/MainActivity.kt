package murmur.sunshine.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import murmur.sunshine.R
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.databinding.ActivityMainBinding
import murmur.sunshine.ui.detail.DetailActivity
import murmur.sunshine.ui.main.ForecastAdapter.ClickHandler
import java.util.Date
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ClickHandler {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil
                .setContentView<ActivityMainBinding>(
                        this, R.layout.activity_main)
    }

    override fun clickEvent(id: Long?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.forecastRecyclerView.setHasFixedSize(true)

        val mainViewModel = ViewModelProviders
                .of(this, factory)[MainViewModel::class.java]

        mainViewModel.forecast.observe(this, Observer {
            it?.forEach {
               Log.d("kanna", "${it.date} ${Date(it.date)} ${it.description} ")
            }
            updateRecyclerView(it)
        })
    }

    private fun updateRecyclerView(list: List<WeatherEntry>?) {
        if (list != null) {
            binding.forecastRecyclerView.adapter = ForecastAdapter(list, this)
        }
    }
}
