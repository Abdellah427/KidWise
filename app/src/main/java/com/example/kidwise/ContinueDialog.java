package com.example.kidwise;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.example.kidwise.playing.CongratulationActivity;

public class ContinueDialog {

    public static void showContinueDialog(Context context, String message, Button nextButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.congratulations_title));
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.continue_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextButton.performClick();
            }
        });
        builder.setNegativeButton(context.getString(R.string.finish_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, CongratulationActivity.class);
                intent.putExtra(CongratulationActivity.EXTRA_MESSAGE, context.getString(R.string.just_congratulations_message));
                context.startActivity(intent);
            }
        });
        builder.show();
    }

}