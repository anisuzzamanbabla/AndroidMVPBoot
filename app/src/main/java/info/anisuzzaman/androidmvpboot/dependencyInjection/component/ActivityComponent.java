package info.anisuzzaman.androidmvpboot.dependencyInjection.component;


import javax.inject.Singleton;

import dagger.Component;
import info.anisuzzaman.androidmvpboot.dependencyInjection.module.ActivityModule;
import info.anisuzzaman.androidmvpboot.ui.UserActivity;

/**
 * Created by anisuzzaman on 18/9/20.
 */

@Singleton
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(UserActivity userActivity);

}
