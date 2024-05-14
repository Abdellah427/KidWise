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
    private String[] words;

    private Button nextButton;
    private TextView wordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_spell_l);

        initializeTextToSpeech();

        nextButton = findViewById(R.id.button_next);
        wordTextView = findViewById(R.id.textView_word);
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
        wordTextView.setText(currentWord);
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
