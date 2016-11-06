package com.example.jason.mocksample;

/**
 * Created by jsson on 16/11/6.
 */
public interface NetworkCallBack {
    void onSuccess(Object data);

    void onFailed(int code, String msg);

}
