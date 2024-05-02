package com.example.kidwise.learning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.ContinueDialog;
import com.example.kidwise.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimilarPicturesActivity extends AppCompatActivity {

    private ImageView mainImageView, optionOne, optionTwo, optionThree;
    private List<ImageView> options;
    private int currentRound = 0;
    private Button resetButton;

    private final int[][] imageIds = {
            { R.drawable.imagesimilar_a1, R.drawable.imagesimilar_a2, R.drawable.imagesimilar_a3 },
            { R.drawable.imagesimilar_b1, R.drawable.imagesimilar_b2, R.drawable.imagesimilar_b3 },
            { R.drawable.imagesimilar_c1, R.drawable.imagesimilar_c2, R.drawable.imagesimilar_c3 },
            { R.drawable.imagesimilar_d1, R.drawable.imagesimilar_d2, R.drawable.imagesimilar_d3 }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures);

        mainImageView = findViewById(R.id.mainImageView);
        optionOne = findViewById(R.id.optionOne);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);

        options = new ArrayList<>();
        options.add(optionOne);
        options.add(optionTwo);
        options.add(optionThree);


        resetButton = new Button(this);
        resetButton.setText("Reset");
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        setupRound();

        View.OnClickListener listener = view -> {
            ImageView selectedOption = (ImageView) view;
            handleAnswer(selectedOption);
        };

        for (ImageView option : options) {
            option.setOnClickListener(listener);
        }
    }

    private void setupRound() {



        int[] randomNumbers = new int[2];
        int[] mainImageIndex = new int[2];
        int[] correctAnswerIndex = new int[2];
        randomNumbers[0] = (int) (Math.random() * 3);

        do {
            randomNumbers[1] = (int) (Math.random() * 3);
        } while (randomNumbers[1] == randomNumbers[0]);

        int rowPrint = (int) (Math.random() * 4);
        mainImageIndex[0] = rowPrint;
        mainImageIndex[1] = randomNumbers[1] ;
        correctAnswerIndex[0] = rowPrint;
        correctAnswerIndex[1] = randomNumbers[0];

        mainImageView.setImageResource(imageIds[mainImageIndex[0]][mainImageIndex[1]]);

        List<Integer> answers = new ArrayList<>();

        answers.add(imageIds[correctAnswerIndex[0]][correctAnswerIndex[1]]);
        int j=-1;
        for (int i = 0; i < 2; i++) {
            int differentImageIndex = generateRandomNumber(0,2,-1);
            j=generateRandomNumber(0,3,correctAnswerIndex[0],j);
            answers.add(imageIds[j][differentImageIndex]);
        }


        Collections.shuffle(answers);




        for (int i = 0; i < options.size(); i++) {
            options.get(i).setImageResource(answers.get(i));
            ;
            if (answers.get(i) == imageIds[correctAnswerIndex[0]][correctAnswerIndex[1]]) {
                options.get(i).setTag(true);
            } else {
                options.get(i).setTag(false);
            }
        }
    }





    private void handleAnswer(ImageView selectedOption) {
        boolean isCorrect = (boolean) selectedOption.getTag();
        if (isCorrect) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            currentRound++;
            if (currentRound < imageIds.length) {
                setupRound();
            } else {
                String message = "You've completed all rounds ! You've completed all rounds !!";
                ContinueDialog.showContinueDialog(this, message, resetButton);

            }
        } else {
            Toast.makeText(this, "Incorrect, try again!", Toast.LENGTH_SHORT).show();
        }
    }


    public static int generateRandomNumber(int n, int m, int x) {
        if (n > m) {
            throw new IllegalArgumentException("La limite inférieure doit être inférieure ou égale à la limite supérieure.");
        }

        Random random = new Random();
        int randomNum;

        do {
            randomNum = random.nextInt((m - n) + 1) + n;
        } while (randomNum == x);

        return randomNum;
    }
    public static int generateRandomNumber(int n, int m, int x1,int x2) {
        if (n > m) {
            throw new IllegalArgumentException("La limite inférieure doit être inférieure ou égale à la limite supérieure.");
        }

        Random random = new Random();
        int randomNum;

        do {
            randomNum = random.nextInt((m - n) + 1) + n;
        } while (randomNum == x1 || randomNum == x2);

        return randomNum;
    }
    public void resetGame(){
        currentRound=0;
    };
}
