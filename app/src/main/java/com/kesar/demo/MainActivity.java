package com.kesar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTestActivity(View view) {
        TestActivity.start(this, true);
    }

    public void openTestFragment(View view) {
        TestActivity.start(this, false);
    }
}
