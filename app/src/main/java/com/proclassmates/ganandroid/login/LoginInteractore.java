package com.proclassmates.ganandroid.login;

import android.os.Handler;
import android.text.TextUtils;

/**
 * @name: GanAndroid
 * @desc:
 * @class name:com.proclassmates.ganandroid.login
 * @author: simon
 * @time： 2019-11-23 11:53
 */
public class LoginInteractore {
    interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(username)) {
                            listener.onUsernameError();
                            return;
                        }
                        if (TextUtils.isEmpty(password)) {
                            listener.onPasswordError();
                            return;
                        }
                        listener.onSuccess();
                    }
                }
                , 2000);
    }
}
