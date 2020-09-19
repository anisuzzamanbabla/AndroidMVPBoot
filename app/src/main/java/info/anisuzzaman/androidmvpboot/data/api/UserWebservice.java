package info.anisuzzaman.androidmvpboot.data.api;


import info.anisuzzaman.androidmvpboot.data.database.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anisuzzaman on 18/9/20.
 */

public interface UserWebservice {
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
