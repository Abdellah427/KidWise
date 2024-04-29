package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kidwise.R;

public class DaysOfWeekActivity extends AppCompatActivity {

    private LinearLayout topContainer, bottomContainer;
    private final String[] daysOfWeek = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_of_week); // Assurez-vous que ce fichier de layout existe

        topContainer = findViewById(R.id.top_container);
        bottomContainer = findViewById(R.id.bottom_container);

        setupButtons();
    }

    private void setupButtons() {
        for (String day : daysOfWeek) {
            Button dayButton = new Button(this);
            dayButton.setText(day);
            dayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveButtonToBottomContainer((Button) v);
                }
            });
            topContainer.addView(dayButton);
        }
    }

    private void moveButtonToBottomContainer(Button button) {
        if (topContainer == ((LinearLayout) button.getParent())) {
            topContainer.removeView(button);
            bottomContainer.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveButtonToTopContainer((Button) v);
                }
            });
        } else {
            moveButtonToTopContainer(button);
        }
        checkOrder();
    }

    private void moveButtonToTopContainer(Button button) {
        bottomContainer.removeView(button);
        topContainer.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveButtonToBottomContainer((Button) v);
            }
        });
    }

    private void checkOrder() {
        boolean isInOrder = true;
        int count = bottomContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            Button button = (Button) bottomContainer.getChildAt(i);
            if (!button.getText().toString().equals(daysOfWeek[i])) {
                isInOrder = false;
                break;
            }
        }

        if (isInOrder && count == daysOfWeek.length) {
            Toast.makeText(this, "Congratulations! Days are in the correct order!", Toast.LENGTH_LONG).show();
        }
    }
}
