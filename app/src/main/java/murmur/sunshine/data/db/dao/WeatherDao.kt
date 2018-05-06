package murmur.sunshine.data.db.dao

import android.arch.persistence.room.*
import murmur.sunshine.data.db.entity.WeatherEntry


//https://medium.com/ @aayushid159/java-varargs-vs-kotlin-varargs-spread-operator-in-kotlin-eb45b7a6f465
@Dao
interface WeatherDao {
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun bulkInsert(vararg weather: WeatherEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<WeatherEntry>)

    @Query("SELECT * FROM weather WHERE id = :id")
    fun getWeatherById(id: Long): WeatherEntry

    @Query("SELECT * FROM weather")
    fun getWeatherAll(): List<WeatherEntry>

    @Query("SELECT COUNT(id) FROM weather WHERE :date - date < 86400000")
    fun countAllFutureWeather(date: Long): Int

    @Query("DELETE FROM weather")
    fun deleteAll()

    @Transaction
    fun clearAndInsert(newSessions: List<WeatherEntry>) {
        deleteAll()
        insert(newSessions)
    }
}