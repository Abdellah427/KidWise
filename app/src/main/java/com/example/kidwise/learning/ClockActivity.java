package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.kidwise.CustomClockView;
import com.example.kidwise.R;
import java.util.Random;

public class ClockActivity extends AppCompatActivity {

    private NumberPicker hourPicker, minutePicker;
    private Button nextButton;
    private TextView questionText;
    private CustomClockView customClockView;
    private int currentHour, currentMinute;
    private int questionCount = 0;
    private static final int TOTAL_QUESTIONS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        customClockView = findViewById(R.id.analog_clock);
        nextButton = findViewById(R.id.button_next);
        questionText = findViewById(R.id.textview_question);
        hourPicker = findViewById(R.id.hour_picker);
        minutePicker = findViewById(R.id.minute_picker);

        // Configure the NumberPickers
        hourPicker.setMinValue(1);
        hourPicker.setMaxValue(12);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(0); // Initialize picker to 0

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedHour = hourPicker.getValue();
                int selectedMinute = minutePicker.getValue();
                if (selectedHour == currentHour && selectedMinute == currentMinute) {
                    questionCount++;
                    if (questionCount < TOTAL_QUESTIONS) {
                        generateRandomTime();
                    } else {
                        questionText.setText(getString(R.string.end_of_questions));
                        nextButton.setEnabled(false);
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
}
