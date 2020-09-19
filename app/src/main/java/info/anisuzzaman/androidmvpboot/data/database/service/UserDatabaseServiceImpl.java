package info.anisuzzaman.androidmvpboot.data.database.service;

import android.os.AsyncTask;

import java.util.Date;

import javax.inject.Inject;

import info.anisuzzaman.androidmvpboot.App;
import info.anisuzzaman.androidmvpboot.data.database.callback.DataBaseCallBack;
import info.anisuzzaman.androidmvpboot.data.database.dao.UserDao;
import info.anisuzzaman.androidmvpboot.data.database.entity.User;

/**
 * Created by anisuzzaman on 19/9/20.
 */

public class UserDatabaseServiceImpl implements UserDatabaseService {

    private static final String SAVE = "Save", LOAD = "Load", HAS_DATA = "HasData";

    @Inject
    UserDao userDao;

    @Inject
    public UserDatabaseServiceImpl() {
        if (userDao == null)
            this.userDao = App.getAppInstance().getComponent().provideUserDao();
    }

    @Override
    public void save(User user, DataBaseCallBack dataBaseCallBack) {
        new DatabaseAsyncTask(dataBaseCallBack).execute(SAVE, user);
    }

    @Override
    public void load(String userLogin, DataBaseCallBack dataBaseCallBack) {
        new DatabaseAsyncTask(dataBaseCallBack).execute(LOAD, userLogin);
    }

    @Override
    public void hasUser(String userLogin, Date lastRefreshMax, DataBaseCallBack dataBaseCallBack) {
        new DatabaseAsyncTask(dataBaseCallBack).execute(HAS_DATA, userLogin, lastRefreshMax);
    }

    public class DatabaseAsyncTask extends AsyncTask<Object, Object, Object> {
        private DataBaseCallBack dataBaseCallBack;

        public DatabaseAsyncTask(DataBaseCallBack dataBaseCallBack) {
            this.dataBaseCallBack = dataBaseCallBack;
        }


        @Override
        protected Object doInBackground(Object... objects) {
            String type = (String) objects[0];
            switch (type) {
                case SAVE:
                    User user = (User) objects[1];
                    userDao.save(user);
                    return user;
                case LOAD:
                    return userDao.load((String) objects[1]);
                case HAS_DATA:
                    return userDao.hasUser((String) objects[1], (Date) objects[2]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o != null)
                dataBaseCallBack.onDataLoad(o);
            else
                dataBaseCallBack.onError("Data Base Error!");
        }
    }


}
