package com.example.application.navigationapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.navigationapp.R;

public class Squats extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor acceleroMeter;
    private SensorEventListener acceleroSensorListener;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.squats_layout);


        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),"data: " +
                        bundle.getString("some"),Toast.LENGTH_SHORT).show();}

        }
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Squats.this, acceleroMeter,  sensorManager.SENSOR_DELAY_NORMAL);
        textview = (TextView) findViewById(R.id.textView2);


    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(acceleroSensorListener);
        super.onDestroy();
    }

    int reps = 0;
    int i = 0;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        i++;
        System.out.println(i);
        System.out.println(sensorEvent.values[1]);
        if(sensorEvent.values[1] >= 9.00000) {
            reps++;
        }
        textview.setText(sensorEvent.values[1] + " " +reps);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}