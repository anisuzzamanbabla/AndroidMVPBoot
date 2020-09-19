package info.anisuzzaman.androidmvpboot.dependencyInjection.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.anisuzzaman.androidmvpboot.data.database.service.UserDatabaseService;
import info.anisuzzaman.androidmvpboot.data.database.service.UserDatabaseServiceImpl;
import info.anisuzzaman.androidmvpboot.ui.presenter.UserPresenter;
import info.anisuzzaman.androidmvpboot.ui.presenter.UserPresenterImpl;

/**
 * Created by anisuzzaman on 19/9/20.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @Singleton
    UserPresenter provideUserPresenter() {
        return new UserPresenterImpl();
    }

    @Provides
    Context providesContext() {
        return mActivity;
    }
}
