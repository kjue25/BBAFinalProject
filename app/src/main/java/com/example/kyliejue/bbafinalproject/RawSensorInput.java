package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Map;

public class RawSensorInput implements SensorEventListener, SensorInput {
    private SensorManager sensorManager;
    private Sensor sensor;
    private Map parameters;
    private float curValue;

    public RawSensorInput(Context context, int sensorType, Map raw_params) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensorType);
        parameters = raw_params;
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        Log.d("STATE", "RAW SENSOR CHANGED");
        curValue = event.values[0];
    }

    @Override
    public String getCurrOutput() {
        return Float.toString(curValue);
    }

    @Override
    public void turnOn() {
        //TODO
    }

    @Override
    public void turnOff() {
        //TODO
    }
}
