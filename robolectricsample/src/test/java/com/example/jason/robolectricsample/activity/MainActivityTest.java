package com.example.jason.robolectricsample.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jason.robolectricsample.BuildConfig;
import com.example.jason.robolectricsample.R;
import com.example.jason.robolectricsample.fragment.MyFragment;
import com.example.jason.robolectricsample.receiver.MyReceiver;

import org.apache.tools.ant.types.resources.comparators.Content;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jsson on 16/5/25.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private Button turnBtn;

    @Before
    public void setUp(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        turnBtn = (Button) mainActivity.findViewById(R.id.btn_login);

    }
    /**
     * 官网Activity测试
     */
    @Test
    public void testActivity(){
        assertNotNull(mainActivity);
        assertEquals(mainActivity.getTitle(), "Robolectric Sample");
    }

    /**
     * 生命周期
     */
    @Test
    public void testLifecycle() {
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class).create().start();
        MainActivity mainActivity = activityController.get();
        TextView textView = (TextView) mainActivity.findViewById(R.id.text_lifecycle);
        assertEquals("onCreate", textView.getText().toString());
        activityController.resume();
        assertEquals("onResume", textView.getText().toString());
        activityController.destroy();
        assertEquals("onDestroy", textView.getText().toString());

    }

    /**
     * Activity 跳转
     */
    @Test
    public void testStartActivity(){
        turnBtn.performClick();
        Intent expectedIntent = new Intent(mainActivity, LoginActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent, actualIntent);
    }

    /**
     * 操作控件
     */
    @Test
    public void testView(){
        CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.checkbox);
        Button btnInverse = (Button) mainActivity.findViewById(R.id.btn_inverse);
        assertTrue(btnInverse.isEnabled());

        checkBox.setChecked(true);
        btnInverse.performClick();
        assertTrue(!checkBox.isChecked());
        btnInverse.performClick();
        assertTrue(checkBox.isChecked());

    }

    /**
     * 资源文件访问
     */
    @Test
    public void testResources() {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        assertEquals("appname", appName);

    }

    /**
     * Fragment
     */
    @Test
    public void testFragment(){
        MyFragment myFragment = new MyFragment();
        SupportFragmentTestUtil.startFragment(myFragment);
        assertNotNull(myFragment.getView());
    }

    /**
     * DelayedTask
     */
    @Test
    public void testDelayedTask() {
        Button delayedTaskBtn = (Button) mainActivity.findViewById(R.id.btn_delay_task);
        assertFalse(mainActivity.isTaskFinsh);
        delayedTaskBtn.performClick();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        assertTrue(mainActivity.isTaskFinsh);
    }

    /**
     * Broadcast Receiver
     */
    @Test
    public void testBroadcast(){
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        String action = "com.example.jason.test.login";
        Intent intent = new Intent(action);
        intent.putExtra("USERNAME", "jasonim");

        //是否注册广播接收者
        assertTrue(shadowApplication.hasReceiverForIntent(intent));

        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application, intent);
        SharedPreferences sharedPreferences = shadowApplication.getSharedPreferences("account", Context.MODE_PRIVATE);
        assertEquals("jasonim", sharedPreferences.getString("USERNAME", ""));

    }
}