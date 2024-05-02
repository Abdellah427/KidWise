package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kidwise.R;

public class MonthsOfYearActivityL extends AppCompatActivity {

    private TextView monthTextView;
    private Button nextButton;

    private int currentMonthIndex = 0;
    private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_of_year_l);

        monthTextView = findViewById(R.id.textView_month);
        nextButton = findViewById(R.id.button_next_month);

        loadMonth(currentMonthIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonthIndex = (currentMonthIndex + 1) % months.length;
                loadMonth(currentMonthIndex);
            }
        });
    }

    private void loadMonth(int index) {
        monthTextView.setText(months[index]);
    }
}
