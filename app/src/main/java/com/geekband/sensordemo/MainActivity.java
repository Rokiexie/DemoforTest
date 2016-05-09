package com.geekband.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    public TextView lightLvl;
    public SensorManager sensorManager;
    public Sensor sensor;
    public ImageView pangzi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLvl = (TextView) findViewById(R.id.light_text);
        pangzi = (ImageView) findViewById(R.id.image);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float value =sensorEvent.values[0];
        lightLvl.setText("光照强度"+value+"lx");
        if(value<100){
            pangzi.setImageResource(R.drawable.pangzi);
        }else {
            pangzi.setImageResource(R.drawable.mm);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
