package com.example.kyliejue.bbafinalproject;

import java.util.ArrayList;
import java.util.HashMap;

public class RawSensorInput implements SensorInput {
    private int sensorType;
    private HashMap<String, ArrayList<String>> parameters;

    public RawSensorInput(int type, HashMap<String, ArrayList<String>> params) {
        sensorType = type;
        parameters = params;
    }

    public int getSensorType() {
        return sensorType;
    }

    public HashMap<String, ArrayList<String>> getParams() {
        return parameters;
    }

    public void setSensorType(int type) {
        sensorType = type;
    }
}
