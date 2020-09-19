package info.anisuzzaman.androidmvpboot.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;

import info.anisuzzaman.androidmvpboot.data.database.entity.User;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by anisuzzaman on 18/9/20.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE login = :userLogin")
    User load(String userLogin);

    @Query("SELECT * FROM user WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    User hasUser(String userLogin, Date lastRefreshMax);
}