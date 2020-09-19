package info.anisuzzaman.androidmvpboot.dependencyInjection.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.anisuzzaman.androidmvpboot.data.DataManager;
import info.anisuzzaman.androidmvpboot.data.api.UserWebservice;
import info.anisuzzaman.androidmvpboot.data.database.MyDatabase;
import info.anisuzzaman.androidmvpboot.data.database.dao.UserDao;
import info.anisuzzaman.androidmvpboot.data.database.service.UserDatabaseService;
import info.anisuzzaman.androidmvpboot.data.database.service.UserDatabaseServiceImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anisuzzaman on 18/9/20.
 */

@Module
public class ApplicationModule {


    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "MyDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(MyDatabase database) {
        return database.userDao();
    }


    @Provides
    @Singleton
    UserDatabaseService provideUserDatabaseService() {
        return new UserDatabaseServiceImpl();
    }


    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    private static String BASE_URL = "https://api.github.com/";

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    UserWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserWebservice.class);
    }
}
