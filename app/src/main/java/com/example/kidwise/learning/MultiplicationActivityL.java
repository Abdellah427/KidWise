package com.example.kidwise.learning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kidwise.R;

public class MultiplicationActivityL extends AppCompatActivity {

    private TextView multiplicationTextView;
    private Button nextButton;

    private int currentTable = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_l);

        multiplicationTextView = findViewById(R.id.textView_multiplication);
        nextButton = findViewById(R.id.button_next_multiplication);

        loadMultiplicationTable(currentTable);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTable++;
                if (currentTable > 10) {
                    currentTable = 1;
                }
                loadMultiplicationTable(currentTable);
            }
        });
    }

    private void loadMultiplicationTable(int table) {
        StringBuilder builder = new StringBuilder();
        int i;
        for (i = 1; i < 10; i++) {

            String multiplication = String.format("%d x %2d  = %2d", table, i, table * i);
            builder.append(multiplication).append("\n");
        }
        i=10;
        String multiplication = String.format("%d x %2d = %2d", table, i, table * i);
        builder.append(multiplication).append("\n");
        multiplicationTextView.setText(builder.toString());
    }

}
