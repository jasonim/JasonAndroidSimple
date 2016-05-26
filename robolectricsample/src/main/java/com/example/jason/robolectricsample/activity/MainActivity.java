package com.example.jason.robolectricsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jason.robolectricsample.R;
import com.example.jason.robolectricsample.activity.LoginActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView lifecycleTextView;
    private CheckBox inverseCheckBox;
    public  boolean isTaskFinsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifecycleTextView = (TextView)findViewById(R.id.text_lifecycle);
        lifecycleTextView.setText("onCreate");
        inverseCheckBox = (CheckBox)findViewById(R.id.checkbox);
        findViewById(R.id.btn_login).setOnClickListener(this);

        findViewById(R.id.btn_inverse).setOnClickListener(this);

        findViewById(R.id.btn_callback).setOnClickListener(this);
        findViewById(R.id.btn_delay_task).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleTextView.setText("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleTextView.setText("onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_inverse:
                inverseCheckBox.setChecked(!inverseCheckBox.isChecked());
                break;
            case R.id.btn_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_callback:
                startActivity(new Intent(this, CallbackActivity.class));
                break;
            case R.id.btn_delay_task:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       isTaskFinsh = true;
                    }
                }, 2000);
                break;
        }
    }
}
