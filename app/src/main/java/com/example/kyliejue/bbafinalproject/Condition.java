package com.example.kyliejue.bbafinalproject;

import android.util.Log;

public class Condition {
    SensorInput sensor;
    private String operand;
    private String chosen_val;

    public Condition() {
        operand = "";
        chosen_val = "";
    }

    // The following methods will be called from the InTile page
    public void updateSensor() {

    }

    public void updateOperand(String op) {
        operand = op;
    }

    public void updateChosenVal(String chosen) {
        chosen_val = chosen;
    }

    public boolean evaluate() {
        switch (operand) {
            case "<":
                if (sensor.getCurrOutput() < chosen_val) return true;
                return false;
            case ">":
                if (sensor.getCurrOutput() > chosen_val) return true;
                return false;
            case "=":
                if (sensor.getCurrOutput() == chosen_val) return true;
                return false;
            default:
                Log.d("STATE", "Invalid operand");
                break;
        }
    }
}
