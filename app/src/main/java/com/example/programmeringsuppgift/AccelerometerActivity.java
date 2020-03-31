package com.example.programmeringsuppgift;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textViewX, textViewY, textViewZ, textViewResult;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        vibrator = (Vibrator) getSystemService(Activity.VIBRATOR_SERVICE);

        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);
        textViewResult = findViewById(R.id.textView2);

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        textViewX.setText("X: " + df.format(x));
        textViewY.setText("Y: " + df.format(y));
        textViewZ.setText("Z: " + df.format(z));

        String position = "Boat is stable";
        textViewResult.setTextColor(Color.parseColor("#083F83"));

        if (y > 4)
            position = "Boat is leaning backwards";
        if (y < -4)
            position = "Boat is leaning forward";
        if (x > 4)
            position = "Boat is leaning left";
        if(x < -4)
            position = "Boat is leaning right";
        if (y > 8 || y < -8 || x > 8 || x < -8) {
            position = "Man over board!";
            vibrator.vibrate(500);
            textViewResult.setTextColor(Color.rgb(200,0,0));
        }
        textViewResult.setText(position);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
