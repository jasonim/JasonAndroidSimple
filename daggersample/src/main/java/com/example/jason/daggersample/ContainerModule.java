package com.example.jason.daggersample;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jsson on 16/5/8.
 */
@Module
public class ContainerModule {
    @Provides
    ShoppingCartModel provideCartModel() {
        return new ShoppingCartModel();
    }
}