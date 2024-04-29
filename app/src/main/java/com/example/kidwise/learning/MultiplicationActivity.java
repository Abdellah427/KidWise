package com.example.kidwise.learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.ContinueDialog;
import com.example.kidwise.R;

import java.util.Random;

public class MultiplicationActivity extends AppCompatActivity {

    private TextView multiplicationQuestion;
    private EditText answerField;
    private static int currentQuestionIndex = 0;
    private final int totalQuestions = 1;
    private int currentCorrectAnswer;
    private Button resetButton;

    private Question[] questions = new Question[totalQuestions];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        multiplicationQuestion = findViewById(R.id.multiplicationQuestion);
        answerField = findViewById(R.id.answerField);
        resetButton = new Button(this);
        resetButton.setText("Reset");
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        generateQuestions();
        showQuestion();
    }

    private void generateQuestions() {
        Random random = new Random();
        for (int i = 0; i < totalQuestions; i++) {
            int factor1 = random.nextInt(10) + 1;
            int factor2 = random.nextInt(10) + 1;
            questions[i] = new Question(factor1, factor2, factor1 * factor2);
        }
    }

    private void showQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            Question question = questions[currentQuestionIndex];
            multiplicationQuestion.setText(question.factor1 + " x " + question.factor2 + " = ?");
            currentCorrectAnswer = question.answer;
        } else {
            String message = "Congratulations! You've successfully completed the multiplication!";
            ContinueDialog.showContinueDialog(this, message, resetButton);

        }
    }

    public void checkAnswer(View view) {
        try {
            int userAnswer = Integer.parseInt(answerField.getText().toString());
            if (userAnswer == currentCorrectAnswer) {
                currentQuestionIndex++;
                showQuestion();
            } else {
                Toast.makeText(this, "Incorrect, try again!", Toast.LENGTH_SHORT).show();
            }
            answerField.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a number.", Toast.LENGTH_SHORT).show();
        }
    }



    static class Question {
        int factor1;
        int factor2;
        int answer;

        Question(int factor1, int factor2, int answer) {
            this.factor1 = factor1;
            this.factor2 = factor2;
            this.answer = answer;
        }
    }
    private void resetGame() {
        currentQuestionIndex = 0;
        resetButton.setVisibility(View.GONE);
        generateQuestions();
        showQuestion();}
}
