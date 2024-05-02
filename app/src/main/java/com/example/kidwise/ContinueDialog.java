package com.example.kidwise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.example.kidwise.learning.CongratulationActivity;

public class ContinueDialog {

    public static void showContinueDialog(Context context, String message, Button nextButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Congratulations!");
        builder.setMessage(message);
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextButton.performClick();
            }
        });
        builder.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, CongratulationActivity.class);
                intent.putExtra(CongratulationActivity.EXTRA_MESSAGE, "Congratulations!");
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
