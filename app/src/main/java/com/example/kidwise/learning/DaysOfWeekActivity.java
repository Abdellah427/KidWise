package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

import com.example.kidwise.R;

public class DaysOfWeekActivity extends AppCompatActivity {

    private LinearLayout left_container, right_container;
    private final String[] daysOfWeek = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_of_week);

        left_container = findViewById(R.id.left_container);
        right_container = findViewById(R.id.right_container);

        setupButtons();
    }

    private void setupButtons() {
        ArrayList<String> daysList = new ArrayList<>();
        for (String day : daysOfWeek) {
            daysList.add(day);
        }
        Collections.shuffle(daysList);

        for (String day : daysList) {
            Button dayButton = new Button(this);
            dayButton.setText(day);
            dayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveButtonToRightContainer((Button) v);
                }
            });
            left_container.addView(dayButton);
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
            if (!button.getText().toString().equals(daysOfWeek[i])) {
                isInOrder = false;
                break;
            }
        }

        if (isInOrder && count == daysOfWeek.length) {
            resetOrder();
            Intent intent = new Intent(getApplicationContext(), CongratulationActivity.class);
            intent.putExtra(CongratulationActivity.EXTRA_MESSAGE, "Congratulations! Days are in the correct order!");
            startActivity(intent);
            finish();


        } else if (count == daysOfWeek.length) {
            Toast.makeText(this, "Try again ! Days are not in the correct order.", Toast.LENGTH_LONG).show();
            resetOrder();
        }
    }

    private void resetOrder() {
        left_container.removeAllViews();
        right_container.removeAllViews();
        setupButtons();
    }
}
