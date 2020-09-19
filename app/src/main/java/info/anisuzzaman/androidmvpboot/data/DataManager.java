package info.anisuzzaman.androidmvpboot.data;

import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.anisuzzaman.androidmvpboot.App;
import info.anisuzzaman.androidmvpboot.data.api.UserWebservice;
import info.anisuzzaman.androidmvpboot.data.database.callback.DataBaseCallBack;
import info.anisuzzaman.androidmvpboot.data.database.entity.User;
import info.anisuzzaman.androidmvpboot.data.database.service.UserDatabaseService;
import info.anisuzzaman.androidmvpboot.listener.DataListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anisuzzaman on 19/9/20.
 */

@Singleton
public class DataManager {

    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final UserWebservice webservice;
    private final UserDatabaseService userDatabaseService;
    private final Executor executor;

    @Inject
    public DataManager(UserWebservice webservice, UserDatabaseService userDatabaseService, Executor executor) {
        this.webservice = webservice;
        this.userDatabaseService = userDatabaseService;
        this.executor = executor;
    }

    public void getUser(String userLogin, DataListener dataListener) {

        userDatabaseService.hasUser(userLogin, getMaxRefreshTime(new Date()),
                new DataBaseCallBack() {
                    @Override
                    public void onDataLoad(Object data) {
                        boolean userExists = ((User) data) != null;
                        if (!userExists) {
                            getUserFromAPI(userLogin, dataListener);
                        } else {
                            userDatabaseService.load(userLogin, new DataBaseCallBack() {
                                @Override
                                public void onDataLoad(Object data) {
                                    Toast.makeText(App.context, "Data refreshed from Database!", Toast.LENGTH_SHORT).show();
                                    dataListener.onResponse(data);
                                }

                                @Override
                                public void onError(String error) {
                                    dataListener.onError(error);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(String error) {
                        getUserFromAPI(userLogin, dataListener);
                    }
                });

    }


    private void getUserFromAPI(String userLogin, DataListener dataListener) {

        webservice.getUser(userLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(App.context, "Data refreshed from network!", Toast.LENGTH_SHORT).show();
                User user = response.body();
                user.setLastRefresh(new Date());
                userDatabaseService.save(user, new DataBaseCallBack() {
                    @Override
                    public void onDataLoad(Object data) {
                        dataListener.onResponse(user);
                    }

                    @Override
                    public void onError(String error) {
                        dataListener.onError(error);
                    }
                });

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dataListener.onError(t.getMessage());
            }
        });
    }

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
