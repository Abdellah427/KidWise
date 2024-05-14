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


    private  String[] directionsWithout_ ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_l);

        imageViewDirection = findViewById(R.id.imageView_direction);
        nextButton = findViewById(R.id.button_next);
        directionTextView = findViewById(R.id.textView_direction);

        directionsWithout_ = new String[]{
                getString(R.string.behind_without_),
                getString(R.string.between_without_),
                getString(R.string.in_without_),
                getString(R.string.in_front_without_),
                getString(R.string.on_without_),
                getString(R.string.to_the_left_without_),
                getString(R.string.to_the_right_without_),
                getString(R.string.under_without_)
        };

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
