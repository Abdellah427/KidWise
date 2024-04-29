package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kidwise.ContinueDialog;
import com.example.kidwise.learning.DigitalClockActivity;
import com.example.kidwise.CustomClockView;
import com.example.kidwise.R;

import java.util.Random;

public class ClockActivity extends AppCompatActivity {

    private Button nextButton;
    private TextView questionText;
    private TextView answer;
    private CustomClockView customClockView;
    private int currentHour, currentMinute;
    private static int questionCount = 0;
    private static final int TOTAL_QUESTIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        customClockView = findViewById(R.id.analog_clock);
        nextButton = findViewById(R.id.button_next);
        questionText = findViewById(R.id.textview_question);
        answer = findViewById(R.id.answer);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writtenTime = answer.getText().toString().trim();
                String analogTime = convertToAnalogTime(currentHour, currentMinute);
                /*
                Log.d("writtenTime", writtenTime);
                Log.d("analogTime", analogTime);
                Log.d("hour", Integer.toString(currentHour));
                Log.d("minute", Integer.toString(currentMinute));
                */
                if (writtenTime.equalsIgnoreCase(analogTime)) {
                    questionCount++;
                    if (questionCount < TOTAL_QUESTIONS) {
                        Intent intent = new Intent(getApplicationContext(), DigitalClockActivity.class);
                        startActivity(intent);
                    } else {
                        nextButton.setEnabled(false);
                        questionCount=0;
                        ContinueDialog.showContinueDialog(ClockActivity.this, "You've completed all questions. Do you want to continue?", nextButton);
                    }
                } else {
                    questionText.setText(getString(R.string.try_again));
                }
            }
        });

        generateRandomTime();
    }

    private void generateRandomTime() {
        Random random = new Random();
        currentHour = random.nextInt(12) + 1;
        currentMinute = random.nextInt(60); // Generate minutes from 0 to 59

        customClockView.setTime(currentHour, currentMinute); // Set the time on the custom clock

        questionText.setText(getString(R.string.what_time_is_it));
    }

    private static String convertToAnalogTime(int hour, int minute) {
        String[] numbers = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty"};

        if (hour < 1 || hour > 12 || minute < 0 || minute > 59) {
            return null;
        }

        String analogTime;

        if (minute == 0) {
            analogTime = numbers[hour] + " o'clock";
        } else if (minute == 15) {
            analogTime = "quarter past " + numbers[hour];
        } else if (minute == 30) {
            analogTime = "half past " + numbers[hour];
        } else if (minute == 45) {
            analogTime = "quarter to " + numbers[hour % 12 + 1];
        } else {
            String hourText = (hour % 12 == 0) ? numbers[12] : numbers[hour % 12];
            String minuteText;
            if (minute < 30) {
                if (minute < 20) {
                    minuteText = numbers[minute];
                } else {
                    minuteText = tens[minute / 10];
                    minuteText += (minute % 10 == 0) ? "" : " "+numbers[minute% 10];
                }
                analogTime = minuteText + " past " + hourText;
            } else {
                minute = 60 - minute;
                if (minute < 20) {
                    minuteText = numbers[minute];
                } else {
                    minuteText = tens[minute / 10];
                    minuteText += (minute % 10 == 0) ? "" : " "+numbers[minute% 10];
                }
                analogTime = minuteText + " to " + numbers[hour % 12 + 1];
            }
        }

        return analogTime;
    }
}