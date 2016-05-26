package com.example.jason.robolectricsample.shadow;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by jsson on 16/5/25.
 */
@Implements(Person.class)
public class ShadowPerson {
    @Implementation
    public String getName() {
        return "jason";
    }
}
