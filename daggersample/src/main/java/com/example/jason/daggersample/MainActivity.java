package com.example.jason.daggersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private ActivityComponent mActivityComponent;

    @Inject ShoppingCartModel startCart;
    @Inject
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        ContainerComponent containerComponent = DaggerContainerComponent.builder().activityComponent(mActivityComponent).containerModule(new ContainerModule()).build();

        containerComponent.inject(this);
        Log.d(TAG, userInfo.name + userInfo.age);

    }
}
