package info.anisuzzaman.androidmvpboot.data.database.service;

import java.util.Date;

import info.anisuzzaman.androidmvpboot.data.database.callback.DataBaseCallBack;
import info.anisuzzaman.androidmvpboot.data.database.entity.User;

/**
 * Created by anisuzzaman on 19/9/20.
 */

public interface UserDatabaseService {
    void save(User user, DataBaseCallBack dataBaseCallBack);

    void load(String userLogin, DataBaseCallBack dataBaseCallBack);

    void hasUser(String userLogin, Date lastRefreshMax, DataBaseCallBack dataBaseCallBack);
}
