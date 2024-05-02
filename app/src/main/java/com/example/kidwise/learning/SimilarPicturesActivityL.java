package com.example.kidwise.learning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;

public class SimilarPicturesActivityL extends AppCompatActivity {

    private ImageView mainImageView, optionOne, optionTwo;
    private TextView themeTextView;
    private int currentRound = 0;

    private final int[][] imageIds = {
            { R.drawable.imagesimilar_a1, R.drawable.imagesimilar_a2, R.drawable.imagesimilar_a3 }, // Ville
            { R.drawable.imagesimilar_b1, R.drawable.imagesimilar_b2, R.drawable.imagesimilar_b3 }, // Nature
            { R.drawable.imagesimilar_c1, R.drawable.imagesimilar_c2, R.drawable.imagesimilar_c3 }, // Animal
            { R.drawable.imagesimilar_d1, R.drawable.imagesimilar_d2, R.drawable.imagesimilar_d3 }  // Hightech
    };

    private final String[] themes = {"Ville", "Nature", "Animal", "Hightech"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures_l);

        mainImageView = findViewById(R.id.mainImageView);
        optionOne = findViewById(R.id.optionOne);
        optionTwo = findViewById(R.id.optionTwo);
        themeTextView = findViewById(R.id.themeTextView);

        setupRound();

        View.OnClickListener nextButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRound++; // Increment round counter
                setupRound(); // Setup next round
            }
        };

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(nextButtonClickListener);
    }

    private void setupRound() {
        int themeIndex = currentRound % themes.length;
        themeTextView.setText(themes[themeIndex]);

        int[] imageIdSet = imageIds[themeIndex];
        mainImageView.setImageResource(imageIdSet[0]);
        optionOne.setImageResource(imageIdSet[1]);
        optionTwo.setImageResource(imageIdSet[2]);
    }
}
