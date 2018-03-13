package com.example.kyliejue.bbafinalproject;

public class Domino {
    Condition[] inputs;
    Output output;
    private boolean isOn;

    public Domino() {
        inputs = new Condition[0];
        output = null;
        isOn = false;
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
