package com.example.ubuntu.sensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Animation extends Activity implements SensorEventListener{
    private float NOISE=2.0f,x,y,z,delta_x,delta_y,delta_z,init_x,init_y,init_z;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isInitialized;
    RelativeLayout li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isInitialized=false;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        li=(RelativeLayout) findViewById(R.id.activity_animation);

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

        int i,j,k;
        i= (int) (delta_x*delta_x);
        j=(int )(delta_y*delta_y);
        k=(int )(delta_z*delta_z);
        if((i+40)<255)
             i=i+40;
        if((j+40)<255)
            j=j+40;
        if((k+40)<255)
            k=k+40;
        if(i<255 && j<255 && k<255 )
        {String string_x,string_y,string_z,finalcolor;
        string_x=Integer.toHexString(i);
        string_y=Integer.toHexString(j);
        string_z=Integer.toHexString(k);
        if(string_x.length()<2)
            string_x="0"+string_x;
        if(string_y.length()<2)
            string_y="0"+string_y;
        if(string_z.length()<2)
            string_z="0"+string_z;
        finalcolor="#"+string_x+string_y+string_z;
        li.setBackgroundColor(Color.parseColor(finalcolor));
        li.postInvalidate();
    }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
