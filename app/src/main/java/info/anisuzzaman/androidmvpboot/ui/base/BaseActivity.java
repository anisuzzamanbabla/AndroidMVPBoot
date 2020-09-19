package info.anisuzzaman.androidmvpboot.ui.base;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import info.anisuzzaman.androidmvpboot.App;
import info.anisuzzaman.androidmvpboot.dependencyInjection.component.ActivityComponent;
import info.anisuzzaman.androidmvpboot.dependencyInjection.component.DaggerActivityComponent;
import info.anisuzzaman.androidmvpboot.dependencyInjection.module.ActivityModule;
import info.anisuzzaman.androidmvpboot.util.DialogUtils;

/**
 * Created by anisuzzaman on 18/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ActivityComponent mActivityComponent;
    private ProgressDialog progressDialog;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = DialogUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

}
