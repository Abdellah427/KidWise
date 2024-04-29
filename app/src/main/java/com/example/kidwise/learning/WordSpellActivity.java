package com.example.kidwise.learning;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.ContinueDialog;
import com.example.kidwise.R;

import java.util.Locale;
import java.util.Random;

public class WordSpellActivity extends AppCompatActivity {

    private EditText spellingInput;
    private TextToSpeech tts;
    private String currentWord;
    private String[] words = {"example", "communication", "international", "development", "environment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_spell);

        spellingInput = findViewById(R.id.spellingInput);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                pickWordAndSpeak();
            }
        });
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
            ContinueDialog.showContinueDialog(this, "Congratulations! You've spelled the word correctly!", null);
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
