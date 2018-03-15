package com.example.kyliejue.bbafinalproject;

import android.util.Log;

import java.io.Serializable;

public class Condition implements Serializable {
    //SensorInput sensor; //TODO: Restore when(if) Conditions/SensorInputs are Parcelable
    private int sensor;
    private String operand;
    private String chosenValue;

    public Condition() {
        sensor = -1;
        operand = "";
        chosenValue = "";
    }

    public Condition(int sense, String op, String chosenVal) {
        sensor = sense;
        operand = op;
        chosenValue = chosenVal;
    }

    // The following methods will be called from the InTile page
    public void updateSensor(int newSensor) {
        sensor = newSensor;
    }

    public void updateOperand(String op) {
        operand = op;
    }

    public void updateChosenVal(String chosen) {
        chosenValue = chosen;
    }

    public String getOp() {
        return operand;
    }

    public String getChosenValue() {
        return chosenValue;
    }

    public int getSensor() {
        return sensor;
    }

    // TODO: Restore when(if) Conditions/SensorInputs are Parcelable
    public boolean evaluate(float sensorVal) {
//        double sensorVal = Double.parseDouble(sensor.getCurrOutput());
        double chosenVal = Double.parseDouble(chosenValue);
        switch (operand) {
            case "<":
                if (sensorVal < chosenVal) return true;
                return false;
            case ">":
//                Log.d("STATE", "EVALUATE: " + sensorVal + " > " + chosenVal);
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
