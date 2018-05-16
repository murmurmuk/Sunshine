package murmur.sunshine.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import murmur.sunshine.data.db.dao.WeatherDao
import murmur.sunshine.data.db.entity.WeatherEntry

@Database(entities = [WeatherEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "weather"
    }
}