package com.example.jason.robolectricsample.shadow;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.internal.SdkConfig;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;
import org.robolectric.internal.bytecode.ShadowMap;

/**
 * Created by jsson on 16/5/25.
 */
public class CustonShadowTestRunner extends RobolectricGradleTestRunner {
    public CustonShadowTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public InstrumentationConfiguration createClassLoaderConfig() {
        InstrumentationConfiguration.Builder builder = InstrumentationConfiguration.newBuilder();
        /**
         * 添加shadow的对象
         */
        builder.addInstrumentedClass(Person.class.getName());
        return builder.build();
    }
}
