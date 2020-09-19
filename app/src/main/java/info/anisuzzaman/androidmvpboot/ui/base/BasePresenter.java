package info.anisuzzaman.androidmvpboot.ui.base;

/**
 * Created by anisuzzaman on 18/9/20.
 */

public interface BasePresenter {

    void attachView(BaseView view);

    void detachView();
}
