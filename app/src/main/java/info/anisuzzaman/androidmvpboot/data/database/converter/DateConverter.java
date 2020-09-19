package info.anisuzzaman.androidmvpboot.data.database.converter;


import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by anisuzzaman on 18/9/20.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
