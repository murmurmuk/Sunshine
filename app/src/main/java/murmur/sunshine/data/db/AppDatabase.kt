package murmur.sunshine.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import murmur.sunshine.data.db.dao.WeatherDao
import murmur.sunshine.data.db.entity.WeatherEntry

@Database(entities = [WeatherEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        private const val DATABASE_NAME = "weather"
        private var instance: AppDatabase? = null
        private val lock = Object()

        @JvmStatic
        fun getInstance(ctx: Context): AppDatabase {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(ctx.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME).build()
                }
            }
            return instance!!
        }
    }
}