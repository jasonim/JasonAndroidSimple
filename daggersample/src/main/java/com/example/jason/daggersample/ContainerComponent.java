package com.example.jason.daggersample;

import dagger.Component;

/**
 * Created by jsson on 16/5/8.
 */
@Component(dependencies = ActivityComponent.class, modules = ContainerModule.class)
public interface ContainerComponent {
    void inject(MainActivity mainActivity);

    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static ContainerComponent init(ActivityComponent component) {
            return DaggerContainerComponent.builder().
                    activityComponent(component).containerModule(new ContainerModule()).build();
        }
    }
}