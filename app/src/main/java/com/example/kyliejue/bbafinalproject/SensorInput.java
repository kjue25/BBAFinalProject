package com.example.kyliejue.bbafinalproject;

import java.io.Serializable;
import java.util.Map;

// SHOULD THIS BE AN ABSTRACT CLASS???
public interface SensorInput extends Serializable {
    Map parameters = null;

    String getCurrOutput();

    void turnOn();

    void turnOff();
}
