package com.example.ubuntu.sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private float NOISE=2.0f,x,y,z,delta_x,delta_y,delta_z,init_x,init_y,init_z;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isInitialized;
    TextView x_tv,y_tv,z_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isInitialized=false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        x_tv=(TextView)findViewById(R.id.x);
        y_tv=(TextView)findViewById(R.id.y);
        z_tv=(TextView)findViewById(R.id.z);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        x=event.values[0];
        y=event.values[1];
        z=event.values[2];
        if(!isInitialized)
        {
            delta_x=0.0f;
            delta_y=0.0f;
            delta_z=0.0f;
            isInitialized=true;
            init_x=x;
            init_y=y;
            init_z=z;
        }
        else {
            delta_x=init_x-x;
            delta_y=init_y-y;
            delta_z=init_z-z;

        }
        x_tv.setText(Float.toString(delta_x));
        y_tv.setText(Float.toString(delta_y));
        z_tv.setText(Float.toString(delta_z));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void launchAnimation(View view)
    {
        Intent intent=new Intent(this,Animation.class);
        startActivity(intent);

    }
}
