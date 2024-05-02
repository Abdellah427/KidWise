package com.example.kidwise.learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;
import com.example.kidwise.redirect.HomeActivity;

public class CongratulationActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.kidwise.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        TextView congratulationTextView = findViewById(R.id.congratulation_text);
        congratulationTextView.setText(message);

        Button redirectButton = findViewById(R.id.redirect_button);
        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToHome();
            }
        });
    }

    private void redirectToHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
