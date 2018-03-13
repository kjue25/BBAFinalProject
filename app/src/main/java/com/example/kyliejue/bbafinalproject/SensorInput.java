package com.example.kyliejue.bbafinalproject;

import java.util.Map;

// SHOULD THIS BE AN ABSTRACT CLASS???
public interface SensorInput {
    Map parameters = null;

    String getCurrOutput();

    void turnOn();

    void turnOff();
}
