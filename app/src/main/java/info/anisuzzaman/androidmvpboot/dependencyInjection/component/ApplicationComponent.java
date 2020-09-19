package info.anisuzzaman.androidmvpboot.dependencyInjection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import info.anisuzzaman.androidmvpboot.App;
import info.anisuzzaman.androidmvpboot.data.DataManager;
import info.anisuzzaman.androidmvpboot.data.database.dao.UserDao;
import info.anisuzzaman.androidmvpboot.dependencyInjection.module.ApplicationModule;

/**
 * Created by anisuzzaman on 18/9/20.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    Context context();
    Application provideApplication();
    DataManager provideDataManager();
    UserDao provideUserDao();
}
