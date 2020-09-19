package info.anisuzzaman.androidmvpboot.ui.view;

import info.anisuzzaman.androidmvpboot.ui.base.BaseView;

/**
 * Created by anisuzzaman on 19/9/20.
 */
public interface UserView extends BaseView {

    void showData(Object data);

    void showError(String error);
}
