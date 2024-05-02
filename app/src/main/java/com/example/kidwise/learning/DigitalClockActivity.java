package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidwise.R;
import com.example.kidwise.redirect.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class DigitalClockActivity extends AppCompatActivity {

    private TextView digitalClockView;
    private EditText writtenTimeEditText;
    private Button next;
    private int currentHour ;
    private int currentMinute ;
    private String currentDigitalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_clock);

        digitalClockView = findViewById(R.id.digital_clock_view);
        writtenTimeEditText = findViewById(R.id.edittext_written_time);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writtenTime = writtenTimeEditText.getText().toString().trim();
                if (writtenTime.equalsIgnoreCase(currentDigitalTime)) {
                    Intent intent = new Intent(getApplicationContext(), ClockActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect, try again!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        generateRandomTime();
    }

    private void generateRandomTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
        int minute = calendar.get(Calendar.MINUTE);
        Random random = new Random();
        currentHour = random.nextInt(12) + 1;
        currentMinute = random.nextInt(60);
        currentDigitalTime = convertToDigitalTime(currentHour, currentMinute);
        digitalClockView.setText(currentHour+"h"+ currentMinute);
    }

    private static String convertToDigitalTime(int hour, int minute) {

        String[] hourWords = {
                "", "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten", "eleven", "twelve"
        };

        String[] minuteWords = {
                "o'clock", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
                "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five",
                "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine", "thirty",
                "thirty-one", "thirty-two", "thirty-three", "thirty-four", "thirty-five",
                "thirty-six", "thirty-seven", "thirty-eight", "thirty-nine", "forty",
                "forty-one", "forty-two", "forty-three", "forty-four", "forty-five",
                "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty",
                "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five",
                "fifty-six", "fifty-seven", "fifty-eight", "fifty-nine"
        };

        String hourWord = hourWords[hour];
        String minuteWord = minuteWords[minute];

        String analogTime;
        if (minute == 0) {
            analogTime = hourWord + " o'clock";
        } else {analogTime = hourWord + " " + minuteWords[minute];}

        return analogTime;
    }

}
