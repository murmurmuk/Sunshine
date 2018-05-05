package murmur.sunshine.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import murmur.sunshine.data.db.entity.WeatherEntry
import java.util.Date


//https://medium.com/ @aayushid159/java-varargs-vs-kotlin-varargs-spread-operator-in-kotlin-eb45b7a6f465
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg weather: WeatherEntry)

    @Query("SELECT * FROM weather WHERE date = :date")
    fun getWeatherByDate(date: Date): WeatherEntry
}