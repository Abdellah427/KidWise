package com.example.kidwise.redirect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.kidwise.account.LoginActivity;
import com.example.kidwise.R;
import com.example.kidwise.account.RegisterActivity;
import com.example.kidwise.playing.FollowObjectActivity;

import java.util.Locale;

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
    public void onChangeLanguageClick(View view) {
        Resources res = getResources();
        Configuration config = res.getConfiguration();

        if (config.locale.getLanguage().equals("en")) {
            setLocale("fr");
        } else if (config.locale.getLanguage().equals("fr")) {
            setLocale("tr");
        } else {
            setLocale("en");
        }
        recreate();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

}