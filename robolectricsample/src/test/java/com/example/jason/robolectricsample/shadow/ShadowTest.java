package com.example.jason.robolectricsample.shadow;

import android.util.Log;

import com.example.jason.robolectricsample.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowLog;

import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jsson on 16/5/25.
 */
@RunWith(CustonShadowTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {ShadowPerson.class})
public class ShadowTest {
    @Before
    public void setUp() throws URISyntaxException{
        ShadowLog.stream = System.out;
    }
    @Test
    public void testCustomShadow(){
        Person person = new Person("jasonim");
        //getName() 实际上调用ShadowPerson的方法
        Log.d("hujd", "test log");
        assertEquals("jason", person.getName());

        ShadowPerson shadowPerson = (ShadowPerson) ShadowExtractor.extract(person);
        assertEquals("jason", shadowPerson.getName());
    }
}
