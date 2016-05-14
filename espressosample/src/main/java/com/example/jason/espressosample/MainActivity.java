package com.example.jason.espressosample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View submit = findViewById(R.id.btn);
        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickContent();
            }
        });
    }

    private void clickContent() {
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editName = (EditText) findViewById(R.id.edit_name);
        EditText editAge = (EditText) findViewById(R.id.edit_age);
        assert editAge != null;
        int age = Integer.parseInt(editAge.getText().toString().trim());
        assert editName != null;
        String name = editName.getText().toString().trim();
        intent.putExtra("person", new Person(name, age));
        startActivity(intent);
    }

}
