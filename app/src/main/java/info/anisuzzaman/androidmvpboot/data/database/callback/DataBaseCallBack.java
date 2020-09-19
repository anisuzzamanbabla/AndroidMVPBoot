package info.anisuzzaman.androidmvpboot.data.database.callback;

/**
 * Created by anisuzzaman on 19/9/20.
 */

public interface DataBaseCallBack {
    void onDataLoad(Object data);

    void onError(String error);
}
