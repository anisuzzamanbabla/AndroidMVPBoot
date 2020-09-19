package info.anisuzzaman.androidmvpboot.ui.presenter;

import info.anisuzzaman.androidmvpboot.ui.base.BasePresenter;

/**
 * Created by anisuzzaman on 19/9/20.
 */
public interface UserPresenter extends BasePresenter {

    void getUser(String userLogin);
}
