package com.example.kyliejue.bbafinalproject;

import java.util.ArrayList;

public class Domino {
    ArrayList<Condition> inputs;
    Output output;
    private boolean isOn;

    public Domino() {
        inputs = new ArrayList<Condition>();
        output = null;
        isOn = false;
    }

    public void toggle() {
        isOn = !isOn;
    }

    public void turnOnSensors() {
        for (Condition condition : inputs) {
            condition.sensor.turnOn();
        }
    }

    public void turnOffSensors(){
        for (Condition condition : inputs) {
            condition.sensor.turnOff();
        }
    }
}
