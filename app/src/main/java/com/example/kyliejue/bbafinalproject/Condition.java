package com.example.kyliejue.bbafinalproject;

import android.util.Log;

public class Condition {
    SensorInput sensor;
    private String operand;
    private String chosenValue;

    public Condition() {
        sensor = null;
        operand = "";
        chosenValue = "";
    }

    // The following methods will be called from the InTile page
    public void updateSensor() {
        //TODO
    }

    public void updateOperand(String op) {
        operand = op;
    }

    public void updateChosenVal(String chosen) {
        chosenValue = chosen;
    }

    public boolean evaluate() {
        double sensorVal = Double.parseDouble(sensor.getCurrOutput());
        double chosenVal = Double.parseDouble(chosenValue);
        switch (operand) {
            case "<":
                if (sensorVal < chosenVal) return true;
                return false;
            case ">":
                if (sensorVal > chosenVal) return true;
                return false;
            case "=":
                if (sensorVal == chosenVal) return true;
                return false;
            default:
                Log.d("STATE", "Invalid operand");
                return false;
        }
    }
}
