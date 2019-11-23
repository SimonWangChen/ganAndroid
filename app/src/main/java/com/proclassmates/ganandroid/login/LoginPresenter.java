package com.proclassmates.ganandroid.login;

/**
 * @name: GanAndroid
 * @desc:
 * @class name:com.proclassmates.ganandroid.login
 * @author: simon
 * @time： 2019-11-23 12:03
 */
public class LoginPresenter implements LoginInteractore.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractore loginInteractore;

    public LoginPresenter(LoginInteractore loginInteractore1, LoginView loginView1) {
        loginView = loginView1;
        loginInteractore = loginInteractore1;
    }

    public void loginValidate(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractore.login(username, password, this);
    }

    public void distroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {

            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {

            loginView.navigateToHome();
        }
    }
}
