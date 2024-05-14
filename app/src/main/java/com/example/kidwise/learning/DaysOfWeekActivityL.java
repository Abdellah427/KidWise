package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kidwise.R;

public class DaysOfWeekActivityL extends AppCompatActivity {

    private TextView dayTextView;
    private Button nextButton;

    private int currentDayIndex = 0;

    private String[] daysOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_of_week_l);

        dayTextView = findViewById(R.id.textView_day);
        nextButton = findViewById(R.id.button_next_day);
        daysOfWeek = new String[]{
                getString(R.string.monday),
                getString(R.string.tuesday),
                getString(R.string.wednesday),
                getString(R.string.thursday),
                getString(R.string.friday),
                getString(R.string.saturday),
                getString(R.string.sunday)
        };


        loadDay(currentDayIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDayIndex = (currentDayIndex + 1) % daysOfWeek.length;
                loadDay(currentDayIndex);
            }
        });
    }

    private void loadDay(int index) {
        dayTextView.setText(daysOfWeek[index]);
    }
}
