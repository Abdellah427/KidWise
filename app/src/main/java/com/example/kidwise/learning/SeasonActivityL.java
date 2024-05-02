package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.kidwise.R;

public class SeasonActivityL extends AppCompatActivity {

    private VideoView videoView;
    private Button nextButton;
    private TextView seasonTextView;

    private int currentSeasonIndex = 0;
    private int[] seasonVideos = {R.raw.autumn, R.raw.winter, R.raw.spring, R.raw.summer};
    private String[] seasonNames = {"Autumn", "Winter", "Spring", "Summer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_l);

        videoView = findViewById(R.id.videoView);
        nextButton = findViewById(R.id.button_next);
        seasonTextView = findViewById(R.id.textView_season); // Le TextView pour afficher le nom de la saison

        loadSeasonVideo(currentSeasonIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSeasonIndex = (currentSeasonIndex + 1) % seasonVideos.length;
                loadSeasonVideo(currentSeasonIndex);
            }
        });
    }

    private void loadSeasonVideo(int index) {
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + seasonVideos[index]);
        videoView.start();

        // Mettre à jour le texte du TextView pour afficher le nom de la saison correspondante
        seasonTextView.setText(seasonNames[index]);
    }
}
