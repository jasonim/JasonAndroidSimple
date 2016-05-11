package com.example.jason.daggersample;

import dagger.Component;

/**
 * Created by jsson on 16/5/8.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
//    void inject(MainActivity activity);
    UserInfo userInfo();

    final class Initializer {
        private Initializer() {
        } // No instances.

        public static ActivityComponent init() {
            return DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        }
    }
}
