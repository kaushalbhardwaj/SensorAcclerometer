package com.example.home.sensoracclerometer;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl;
    TextView x,y,z;
    SensorManager sm;
    Sensor s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x=(TextView)findViewById(R.id.textView);
        y=(TextView)findViewById(R.id.textView2);
        z=(TextView)findViewById(R.id.textView3);
        rl=(RelativeLayout)findViewById(R.id.rl1);

        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(s!=null) {

            sm.registerListener(mySensor, s, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sensor not found",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    final SensorEventListener mySensor =new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float a[]=event.values;
            float v1,v2,v3;
            v1=a[0];
            v2=a[1];
            v3=a[2];

            x.setText("x="+v1);
            y.setText("y="+v2);
            z.setText("z="+v3);
            if(v1>4.9||v1<-4.9)
            {
                rl.setBackgroundColor(Color.MAGENTA);
            }
            else if(v2>4.9||v2<-4.9)
            {
                rl.setBackgroundColor(Color.LTGRAY);
            }
            else if(v3>4.9||v3<-4.9)
            {
                rl.setBackgroundColor(Color.CYAN);
            }
            else
            {
                rl.setBackgroundColor(Color.WHITE);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
