package murmur.sunshine.ui.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import murmur.sunshine.data.db.entity.WeatherEntry
import murmur.sunshine.databinding.ForecastItemBinding
import murmur.sunshine.util.WeatherEntryUtil

class ForecastAdapter(private val list: List<WeatherEntry>,
                      private val clickHandler: ClickHandler)
    : RecyclerView.Adapter<ForecastViewHolder>() {

    interface ClickHandler {
        fun clickEvent(id: Long?)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ForecastItemBinding.inflate(layoutInflater, parent, false)
        return ForecastViewHolder(binding, clickHandler)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

}


class ForecastViewHolder(private val binding: ForecastItemBinding,
                         private val clickHandler: ForecastAdapter.ClickHandler)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherEntry: WeatherEntry) {
        binding.entry = weatherEntry
        binding.util = WeatherEntryUtil
        binding.icon.setImageResource(
                WeatherEntryUtil.getWeatherIcon(weatherEntry.weatherIconId))
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            Log.d("kanna", "click ${binding.entry?.weatherIconId}")
            clickHandler.clickEvent(binding.entry?.id)
        }
    }
}