package com.example.jason.espressosample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jsson on 16/5/14.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public  void verifyWithRightName(){
        onView(withId(R.id.edit_name)).perform(typeText("android"), closeSoftKeyboard());
        onView(withId(R.id.edit_age)).perform(typeText("wrong"),closeSoftKeyboard());
        onView(withId(R.id.btn)).perform(click());

        onView(withId(R.id.result)).check(matches(withText("ok")));

    }


}
