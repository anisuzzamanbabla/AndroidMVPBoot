package info.anisuzzaman.androidmvpboot;

import android.app.Application;
import android.content.Context;

import info.anisuzzaman.androidmvpboot.dependencyInjection.component.ApplicationComponent;
import info.anisuzzaman.androidmvpboot.dependencyInjection.component.DaggerApplicationComponent;
import info.anisuzzaman.androidmvpboot.dependencyInjection.module.ApplicationModule;

/**
 * Created by anisuzzaman on 18/9/20.
 */

public class App extends Application {
    public static Context context;
    private static App appInstance;
    ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        appInstance=this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }


    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static synchronized App getAppInstance() {
       return appInstance;
    }
}
