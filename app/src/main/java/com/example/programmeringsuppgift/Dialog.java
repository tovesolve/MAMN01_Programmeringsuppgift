package com.example.programmeringsuppgift;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.widget.TextView;

public class Dialog extends AppCompatDialogFragment {
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TextView textView = new TextView(getActivity());
        textView.setText("Make the ship go faster");
        textView.setBackgroundColor(Color.parseColor("#8ACAC1C1"));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30F);
        textView.setGravity(Gravity.CENTER);

        TextView message = new TextView(getActivity());
        message.setText("Today we have a strong north-west wind. \nTo get your ship go fast: \n" +
                "-3 < x < -2 \n" +
                "2 < y < 3 \n" +
                "AHOJ!");
        message.setBackgroundColor(Color.parseColor("#8ACAC1C1"));


        //setTitle("Get the ship go faster")
        builder.setCustomTitle(textView)
                .setMessage("Today we have a strong west wind. \nTo get your ship go fast: \n" +
                        "-3 < x < -2 \n" +
                        "1 < y < 2 \n" +
                        "AHOJ!"
                )
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
