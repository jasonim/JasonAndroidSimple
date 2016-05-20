package com.example.jason.espressosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Person person = getIntent().getParcelableExtra("person");
        if(person.name.equals("jason") && 20 == person.age) {
            TextView textView = (TextView) findViewById(R.id.result);
            assert textView != null;
            textView.setText("ok");
        }
    }
}
