package com.example.kidwise.playing;

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
    private String[] daysOfWeek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_of_week);

        left_container = findViewById(R.id.left_container);
        right_container = findViewById(R.id.right_container);
        daysOfWeek = new String[]{
                getString(R.string.monday),
                getString(R.string.tuesday),
                getString(R.string.wednesday),
                getString(R.string.thursday),
                getString(R.string.friday),
                getString(R.string.saturday),
                getString(R.string.sunday)
        };
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
            intent.putExtra(CongratulationActivity.EXTRA_MESSAGE, getString(R.string.congratulations_message));
            startActivity(intent);
            finish();


        } else if (count == daysOfWeek.length) {
            Toast.makeText(this, getString(R.string.try_again_message), Toast.LENGTH_LONG).show();
            resetOrder();
        }
    }

    private void resetOrder() {
        left_container.removeAllViews();
        right_container.removeAllViews();
        setupButtons();
    }
}
