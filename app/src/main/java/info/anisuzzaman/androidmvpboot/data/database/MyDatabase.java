package info.anisuzzaman.androidmvpboot.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import info.anisuzzaman.androidmvpboot.data.database.converter.DateConverter;
import info.anisuzzaman.androidmvpboot.data.database.dao.UserDao;
import info.anisuzzaman.androidmvpboot.data.database.entity.User;

/**
 * Created by anisuzzaman on 18/9/20.
 */

@Database(entities = {User.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {
    private static volatile MyDatabase myDatabase;

    public abstract UserDao userDao();
}
