package com.example.jason.rxandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupExampleList();

    }

    private void setupExampleList() {
        RecyclerView exampleList = (RecyclerView) findViewById(R.id.example_list);
        exampleList.setHasFixedSize(true);
        exampleList.setLayoutManager(new LinearLayoutManager(this));
        exampleList.setAdapter(new ExampleAdapter(this, getExamples()));
    }

    private static List<ExampleActivityAndName> getExamples() {
        List<ExampleActivityAndName> exampleActivityAndNames = new ArrayList<>();
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example1Activity.class,
                "Example 1: Simple Color List"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example2Activity.class,
                "Example 2: Favorite Tv Shows"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example3Activity.class,
                "Example 3: Improved Favorite Tv Shows"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example4Activity.class,
                "Example 4: Button Counter"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example5Activity.class,
                "Example 5: Value Display"));
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example6Activity.class,
                "Example 6: City Search"));
        return exampleActivityAndNames;
    }

}
