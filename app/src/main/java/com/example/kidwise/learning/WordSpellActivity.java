package com.example.kidwise.learning;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.ContinueDialog;
import com.example.kidwise.R;

import java.util.Locale;
import java.util.Random;
public class WordSpellActivity extends AppCompatActivity {

    private EditText spellingInput;
    private TextToSpeech tts;
    private String currentWord;
    private String[] words = {
            "example", "communication", "international", "development", "environment",
            "programming", "technology", "education", "challenge", "innovation",
            "opportunity", "experience", "knowledge", "creativity", "solution",
            "achievement", "motivation", "inspiration", "success", "imagination"
    };
    private static int correctCount = 0;
    private final int totalQuestions = 5;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_spell);

        spellingInput = findViewById(R.id.spellingInput);
        resetButton = new Button(this);
        resetButton.setText("Reset");
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                pickWordAndSpeak();
            }
        });
    }

    private void resetGame() {
        correctCount = 0;
        resetButton.setVisibility(View.GONE);
        pickWordAndSpeak();
    }

    private void pickWordAndSpeak() {
        int wordIndex = new Random().nextInt(words.length);
        currentWord = words[wordIndex];
        speakWord(null);
    }

    public void speakWord(View view) {
        if (tts != null && currentWord != null) {
            tts.speak(currentWord, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    public void checkSpelling(View view) {
        String userEntry = spellingInput.getText().toString().trim();
        if (userEntry.equalsIgnoreCase(currentWord)) {
            correctCount++;
            if (correctCount == totalQuestions) {
                String message = "Congratulations! You've spelled two words correctly!";
                ContinueDialog.showContinueDialog(this, message, resetButton);
                correctCount = 0;
            } else {
                pickWordAndSpeak();
            }
        } else {
            Toast.makeText(this, "Incorrect, try again!", Toast.LENGTH_SHORT).show();
        }
        spellingInput.setText("");
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
