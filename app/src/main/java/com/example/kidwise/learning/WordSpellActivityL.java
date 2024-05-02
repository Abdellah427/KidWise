package com.example.kidwise.learning;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;

import java.util.Locale;
import java.util.Random;

public class WordSpellActivityL extends AppCompatActivity {

    private TextToSpeech tts;
    private String currentWord;
    private String[] words = {
            "example", "communication", "international", "development", "environment",
            "programming", "technology", "education", "challenge", "innovation",
            "opportunity", "experience", "knowledge", "creativity", "solution",
            "achievement", "motivation", "inspiration", "success", "imagination"
    };
    private Button nextButton;
    private TextView wordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_spell_l);

        initializeTextToSpeech();

        nextButton = findViewById(R.id.button_next);
        wordTextView = findViewById(R.id.textView_word);

        pickWordAndSpeak();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickWordAndSpeak();
            }
        });
    }

    private void initializeTextToSpeech() {
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
        wordTextView.setText(currentWord); // Mettre à jour le texte du TextView
    }

    public void speakWord(View view) {
        if (tts != null && currentWord != null) {
            tts.speak(currentWord, TextToSpeech.QUEUE_FLUSH, null, null);
        }
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
