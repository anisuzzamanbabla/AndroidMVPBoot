package info.anisuzzaman.androidmvpboot.ui.presenter;

import javax.inject.Inject;

import info.anisuzzaman.androidmvpboot.App;
import info.anisuzzaman.androidmvpboot.data.DataManager;
import info.anisuzzaman.androidmvpboot.listener.DataListener;
import info.anisuzzaman.androidmvpboot.ui.base.BaseView;
import info.anisuzzaman.androidmvpboot.ui.view.UserView;

/**
 * Created by anisuzzaman on 19/9/20.
 */

public class UserPresenterImpl implements UserPresenter {
    private UserView userView;

    private DataManager mDataManager;

    @Inject
    public UserPresenterImpl() {
    }


    @Override
    public void getUser(String userLogin) {

        userView.showLoading();
        mDataManager = App.getAppInstance().getComponent().provideDataManager();
        mDataManager.getUser(userLogin, new DataListener() {
            @Override
            public void onResponse(Object data) {
                userView.hideLoading();
                userView.showData(data);
            }

            @Override
            public void onError(String error) {
                userView.hideLoading();
                userView.showError(error);
            }
        });
    }

    @Override
    public void attachView(BaseView view) {
        userView = (UserView) view;
    }

    @Override
    public void detachView() {
        userView = null;
    }
}
