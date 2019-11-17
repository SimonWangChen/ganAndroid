package com.proclassmates.ganandroid;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class DynamicFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
}
