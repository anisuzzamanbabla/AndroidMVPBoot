package info.anisuzzaman.androidmvpboot.listener;

/**
 * Created by anisuzzaman on 19/9/20.
 */
public interface DataListener {
        void onResponse(Object data);
        void onError(String error);
}
