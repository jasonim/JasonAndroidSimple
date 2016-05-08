package com.example.jason.daggersample;

import dagger.Component;

/**
 * Created by jsson on 16/5/8.
 */
@Component(dependencies = ActivityComponent.class, modules = ContainerModule.class)
public interface ContainerComponent {
    void inject(MainActivity mainActivity);
}