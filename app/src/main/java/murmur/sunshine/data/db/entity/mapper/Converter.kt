package murmur.sunshine.data.db.entity.mapper

import android.arch.persistence.room.TypeConverter
import java.util.Date

object Converter {
    @JvmStatic
    @TypeConverter
    fun toDate(timeStamp: Long?): Date? {
        return if (timeStamp == null) {
            null
        } else {
            Date(timeStamp)
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