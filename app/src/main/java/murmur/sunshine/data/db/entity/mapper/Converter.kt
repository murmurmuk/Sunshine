package murmur.sunshine.data.db.entity.mapper

import android.arch.persistence.room.TypeConverter
import java.util.Date
import java.util.concurrent.TimeUnit

object Converter {
    @JvmStatic
    @TypeConverter
    fun toDate(timeStamp: Long?): Date? {
        return if (timeStamp == null) {
            null
        } else {
            Date(TimeUnit.MILLISECONDS.convert(timeStamp, TimeUnit.SECONDS))
        }
    }

    @JvmStatic
    @TypeConverter
    fun toTimeStamp(date: Date?): Long? {
        return if (date == null) {
            null
        } else {
            date.time
        }
    }


}