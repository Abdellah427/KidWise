package com.example.kidwise.redirect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.learning.*;

import com.example.kidwise.R;


public class LearningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        Button clockButton = findViewById(R.id.clockButton);
        Button seasonButton = findViewById(R.id.seasonButton);
        Button daysOfWeekButton = findViewById(R.id.daysOfWeekButton);
        Button monthsOfYearButton = findViewById(R.id.monthsOfYearButton);
        Button digitMemoryButton = findViewById(R.id.digitMemoryButton);
        Button wordSpellButton = findViewById(R.id.wordSpellButton);
        Button directionsButton = findViewById(R.id.directionsButton);
        Button multiplicationButton = findViewById(R.id.multiplicationButton);
        Button similarPicturesButton = findViewById(R.id.similarPicturesButton);
        Button followObjectButton = findViewById(R.id.followObjectButton);

        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, ClockActivity.class));
            }
        });

        seasonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, SeasonActivity.class));
            }
        });

        daysOfWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, DaysOfWeekActivity.class));
            }
        });

        monthsOfYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, MonthsOfYearActivity.class));
            }
        });

        digitMemoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, DigitMemoryActivity.class));
            }
        });

        wordSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, WordSpellActivity.class));
            }
        });

        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, DirectionsActivity.class));
            }
        });

        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, MultiplicationActivity.class));
            }
        });

        similarPicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, SimilarPicturesActivity.class));
            }
        });

        followObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningActivity.this, FollowObjectActivity.class));
            }
        });
    }
}