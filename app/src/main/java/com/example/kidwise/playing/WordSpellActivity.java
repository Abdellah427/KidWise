package com.example.kidwise.playing;

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
    private String[] words;
    private static int correctCount = 0;
    private final int totalQuestions = 5;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_spell);

        spellingInput = findViewById(R.id.spellingInput);
        resetButton = new Button(this);
        resetButton.setText(getString(R.string.reset_button_text));
        words = new String[]{
                getString(R.string.example),
                getString(R.string.communication),
                getString(R.string.international),
                getString(R.string.development),
                getString(R.string.environment),
                getString(R.string.programming),
                getString(R.string.technology),
                getString(R.string.education),
                getString(R.string.challenge),
                getString(R.string.innovation),
                getString(R.string.opportunity),
                getString(R.string.experience),
                getString(R.string.knowledge),
                getString(R.string.creativity),
                getString(R.string.solution),
                getString(R.string.achievement),
                getString(R.string.motivation),
                getString(R.string.inspiration),
                getString(R.string.success),
                getString(R.string.imagination)
        };

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
                String message = getString(R.string.congratulations_message_word, 2);
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
