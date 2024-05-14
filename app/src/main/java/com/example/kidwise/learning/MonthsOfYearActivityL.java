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
    private  String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_of_year_l);

        monthTextView = findViewById(R.id.textView_month);
        nextButton = findViewById(R.id.button_next_month);

        months = new String[]{
                getString(R.string.january),
                getString(R.string.february),
                getString(R.string.march),
                getString(R.string.april),
                getString(R.string.may),
                getString(R.string.june),
                getString(R.string.july),
                getString(R.string.august),
                getString(R.string.september),
                getString(R.string.october),
                getString(R.string.november),
                getString(R.string.december)
        };

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
