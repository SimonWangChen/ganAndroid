package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proclassmates.ganandroid.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton = findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, LoginActivity.class);

                data.putExtra("data", "something you like");

                startActivity(data);
            }
        });
    }
}
