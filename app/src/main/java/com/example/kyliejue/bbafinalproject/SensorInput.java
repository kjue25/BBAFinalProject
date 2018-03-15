package com.example.kyliejue.bbafinalproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

// SHOULD THIS BE AN ABSTRACT CLASS???
public interface SensorInput extends Serializable {
    int sensorType = -1;
    HashMap<String, ArrayList<String>> parameters = null;

    // TODO: Restore if parcelable
//    String getCurrOutput();
//
//    void turnOn();
//
//    void turnOff();
}
