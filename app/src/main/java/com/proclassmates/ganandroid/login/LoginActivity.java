package com.proclassmates.ganandroid.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.proclassmates.ganandroid.main.MainActivity;
import com.proclassmates.ganandroid.R;

/**
 * @name: GanAndroid
 * @desc:
 * @class name:com.proclassmates.ganandroid.login
 * @author: simon
 * @time： 2019-11-23 12:09
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginValidate(username.getText().toString(), password.getText().toString());
            }
        });

        presenter = new LoginPresenter(new LoginInteractore(), this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setText(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setText(getString(R.string.password_error));
    }

    @Override
    protected void onDestroy() {
        presenter.distroy();
        super.onDestroy();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
