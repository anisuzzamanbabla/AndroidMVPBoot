package info.anisuzzaman.androidmvpboot.ui;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.anisuzzaman.androidmvpboot.R;
import info.anisuzzaman.androidmvpboot.data.database.entity.User;
import info.anisuzzaman.androidmvpboot.ui.base.BaseActivity;
import info.anisuzzaman.androidmvpboot.ui.presenter.UserPresenter;
import info.anisuzzaman.androidmvpboot.ui.view.UserView;

public class UserActivity extends BaseActivity implements UserView {

    @Inject
    UserPresenter userPresenter;

    @BindView(R.id.imageViewUser)
    ImageView imageViewUser;
    @BindView(R.id.textViewUserName)
    TextView textViewUserName;
    @BindView(R.id.textViewCompany)
    TextView textViewCompany;
    @BindView(R.id.textViewWebsite)
    TextView textViewWebsite;
    @BindView(R.id.editTextUserLogin)
    TextView editTextUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        initToolBar();
        ButterKnife.bind(this);
        userPresenter.attachView(this);
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @OnClick(R.id.btnLoadData)
    void btnLoadData() {
        userPresenter.getUser(editTextUserLogin.getText().toString().equals("") ? "anisuzzamanbabla" : editTextUserLogin.getText().toString());
    }

    @Override
    public void showData(Object data) {
        User user = (User) data;
        Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(imageViewUser);
        this.textViewUserName.setText(user.getName());
        this.textViewCompany.setText(user.getCompany());
        this.textViewWebsite.setText(user.getBlog());
    }

    @Override
    public void showError(String error) {
        textViewUserName.setText(error);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.detachView();
    }

}