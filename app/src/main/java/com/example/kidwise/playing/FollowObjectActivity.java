package com.example.kidwise.playing;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;

public class FollowObjectActivity extends AppCompatActivity {
    private ImageView ball;
    private TextView instructions;
    private Handler handler;
    private int y=yIni;
    private int  x;
    private int xDirection = 1;
    private int yDirection = 150;
    private static final int yIni = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_object);

        ball = findViewById(R.id.ball);
        instructions = findViewById(R.id.instructions);
        instructions.setText("Follow the ball with your eyes.");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveBall();
                handler.postDelayed(this, 25);
            }
        }, 5000); // 10 millisecond delay
    }

    private void moveBall() {
        x += 5 * xDirection;

        if (x < 0 || x >= getWindowManager().getDefaultDisplay().getWidth() - ball.getWidth()) {
            x = 0;
            y += yDirection;
        }
        if (y < 0 || y >= getWindowManager().getDefaultDisplay().getHeight() - ball.getHeight()) {
            x=0;
            y =yIni;


        }

        ball.setX(x);
        ball.setY(y);
    }
}
