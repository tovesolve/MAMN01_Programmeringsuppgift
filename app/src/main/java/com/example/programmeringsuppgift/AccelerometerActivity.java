package com.example.programmeringsuppgift;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textViewX, textViewY, textViewZ, textViewResult;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private Vibrator vibrator;
    private Button boatbutton;
    boolean haveSensor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        haveSensor = sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        vibrator = (Vibrator) getSystemService(Activity.VIBRATOR_SERVICE);

        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);
        textViewResult = findViewById(R.id.textView2);
        boatbutton = (Button) findViewById(R.id.boatbutton);
        boatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        textViewX.setText("X: " + df.format(x));
        textViewY.setText("Y: " + df.format(y));
        textViewZ.setText("Z: " + df.format(z));
        String position = "Ship is stable";
        textViewResult.setTextColor(Color.parseColor("#083F83"));

        if (y > 3)
            position = "Ship is leaning backwards";
        if (y < -3)
            position = "Ship is leaning forward";
        if (x > 3)
            position = "Ship is leaning left";
        if(x < -3)
            position = "Ship is leaning right";
        if (y > 9 || y < -9 || x > 9 || x < -9) {
            position = "Man over board!";
            vibrator.vibrate(200);
            textViewResult.setTextColor(Color.rgb(200,0,0));
        }
        if (-2 > x && x > -3 && y>1 && y<2) {
            position = "Ship is stable and fast";
            textViewResult.setTextColor(Color.rgb(0,200,0));
        }
        textViewResult.setText(position);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void stop() {
        if(haveSensor){
            sensorManager.unregisterListener(this,sensor);
        }
        vibrator.cancel();
    }

    @Override
    public void onPause(){
        super.onPause();
        stop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
