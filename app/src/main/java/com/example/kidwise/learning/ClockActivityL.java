package com.example.kidwise.learning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;

public class ClockActivityL extends AppCompatActivity {

    private ImageView clockImageView;
    private TextView clockTypeTextView;
    private Button nextButton;

    private boolean isDigitalClock = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_l);

        clockImageView = findViewById(R.id.imageView_clock);
        clockTypeTextView = findViewById(R.id.textView_clock_type);
        nextButton = findViewById(R.id.button_next);

        // Initial setup
        updateClockType();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDigitalClock = !isDigitalClock;
                updateClockType();
            }
        });
    }

    private void updateClockType() {
        if (isDigitalClock) {
            clockImageView.setImageResource(R.drawable.digital_lesson);
            clockTypeTextView.setText(getString(R.string.digital_time));
        } else {
            clockImageView.setImageResource(R.drawable.analog_lesson);
            clockTypeTextView.setText(getString(R.string.analog_time));
        }
    }

}
