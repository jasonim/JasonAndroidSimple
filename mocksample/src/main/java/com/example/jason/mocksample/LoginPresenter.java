package com.example.jason.mocksample;

/**
 * Created by jsson on 16/11/6.
 */
public class LoginPresenter {
    private UserLoginTask mAuthTask;

    public LoginPresenter(UserLoginTask mAuthTask) {
        this.mAuthTask = mAuthTask;
    }


    public void login(String email, String password) {
        //TODO test argument

        if(!NetManager.isConnected()) {
            return;
        }
        //执行登录操作, 并且处理网络返回
        mAuthTask.execute(email, password, new NetworkCallBack() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onFailed(int code, String msg) {

            }
        });
    }
}
