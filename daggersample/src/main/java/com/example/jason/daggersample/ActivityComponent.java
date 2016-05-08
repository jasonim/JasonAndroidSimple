package com.example.jason.daggersample;

import dagger.Component;

/**
 * Created by jsson on 16/5/8.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
//    void inject(MainActivity activity);
    UserInfo userInfo();
}
