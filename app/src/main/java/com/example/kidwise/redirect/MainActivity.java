package com.example.kidwise.redirect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.kidwise.account.LoginActivity;
import com.example.kidwise.R;
import com.example.kidwise.account.RegisterActivity;
import com.example.kidwise.playing.FollowObjectActivity;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        this.login = (Button) findViewById(R.id.login);
        this.register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);

            }
        });
    }

}