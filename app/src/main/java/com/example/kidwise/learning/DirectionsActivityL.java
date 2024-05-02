package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kidwise.R;

public class DirectionsActivityL extends AppCompatActivity {

    private ImageView imageViewDirection;
    private Button nextButton;
    private TextView directionTextView;

    private int currentRound = 0;
    private final int totalRounds = 8;
    private final String[] directions = {"behind", "between", "in", "in_front", "on", "to_the_left", "to_the_right", "under"};
    private final String[] directionsWithout_ = {"Behind", "Between", "In", "In Front", "On", "To The Left", "To The Right", "Under"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_l);

        imageViewDirection = findViewById(R.id.imageView_direction);
        nextButton = findViewById(R.id.button_next);
        directionTextView = findViewById(R.id.textView_direction);

        loadDirectionImage(currentRound);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRound = (currentRound + 1) % totalRounds;
                loadDirectionImage(currentRound);
            }
        });
    }

    private void loadDirectionImage(int round) {
        int imageId = getResources().getIdentifier("direction_" + directions[round], "drawable", getPackageName());
        imageViewDirection.setImageResource(imageId);
        directionTextView.setText(directionsWithout_[round]);
    }
}
