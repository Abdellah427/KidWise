package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

import com.example.kidwise.R;

public class MonthsOfYearActivity extends AppCompatActivity {

    private LinearLayout left_container, right_container;
    private final String[] monthsOfYear = {
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_of_year);

        left_container = findViewById(R.id.left_container);
        right_container = findViewById(R.id.right_container);

        setupButtons();
    }

    private void setupButtons() {
        ArrayList<String> monthsList = new ArrayList<>();
        for (String month : monthsOfYear) {
            monthsList.add(month);
        }
        Collections.shuffle(monthsList); // Mélanger les mois de l'année

        for (String month : monthsList) {
            Button monthButton = new Button(this);
            monthButton.setText(month);
            monthButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveButtonToRightContainer((Button) v);
                }
            });
            left_container.addView(monthButton);
        }
    }

    private void moveButtonToRightContainer(Button button) {
        if (left_container == ((LinearLayout) button.getParent())) {
            left_container.removeView(button);
            right_container.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveButtonToLeftContainer((Button) v);
                }
            });
        } else {
            moveButtonToLeftContainer(button);
        }
        checkOrder();
    }

    private void moveButtonToLeftContainer(Button button) {
        right_container.removeView(button);
        left_container.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveButtonToRightContainer((Button) v);
            }
        });
    }

    private void checkOrder() {
        boolean isInOrder = true;
        int count = right_container.getChildCount();
        for (int i = 0; i < count; i++) {
            Button button = (Button) right_container.getChildAt(i);
            if (!button.getText().toString().equals(monthsOfYear[i])) {
                isInOrder = false;
                break;
            }
        }

        if (isInOrder && count == monthsOfYear.length) {
            Toast.makeText(this, "Congratulations! Months are in the correct order!", Toast.LENGTH_LONG).show();
        }
    }
}
