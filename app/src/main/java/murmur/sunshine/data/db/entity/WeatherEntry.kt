package murmur.sunshine.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity(tableName = "weather", indices = [Index(value = ["date"], unique = true)])
data class WeatherEntry (@PrimaryKey(autoGenerate = true)
                         var id: Long?,
                         val weatherIconId: Int,
                         val date: Date,
                         val min: Double,
                         val max: Double,
                         val humidity: Double,
                         val description: String)