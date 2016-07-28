package com.example.jason.robolectricsample.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jason.robolectricsample.BuildConfig;
import com.example.jason.robolectricsample.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jsson on 16/5/25.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {

    private Button button;
    private EditText emailView;
    private EditText passwordView;

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText)activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);

    }

    @Test
    public void testLoginSuccess(){
        emailView.setText("hk081508@gmail.com");
        passwordView.setText("123");
        button.performClick();
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        assertThat("Next activity has started", shadowApplication.getNextStartedActivity(), is(notNullValue()));
    }
}