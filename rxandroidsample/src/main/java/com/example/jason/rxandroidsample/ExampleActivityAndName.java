package com.example.jason.rxandroidsample;

/**
 * Created by jsson on 16/4/20.
 */

import android.app.Activity;

/**
 * Pair consisting of the name of an example and the activity corresponding to the example.
 */
public class ExampleActivityAndName {

    public final Class<? extends Activity> mExampleActivityClass;
    public final String mExampleName;

    public ExampleActivityAndName(
            Class<? extends Activity> exampleActivityClass,
            String exampleName) {
        mExampleActivityClass = exampleActivityClass;
        mExampleName = exampleName;
    }
}