package com.example.kyliejue.bbafinalproject;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Consider if Parcelable should be used instead of Serializable
public class Domino implements Serializable {
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

//    public void turnOnSensors() {
//        for (Condition condition : inputs) {
//            condition.sensor.turnOn();
//        }
//    }
//
//    public void turnOffSensors(){
//        for (Condition condition : inputs) {
//            condition.sensor.turnOff();
//        }
//    }

    public boolean isOn() {
        return isOn;
    }

    // TODO: Restore if Parcelable
//    public boolean conditionsSatisfied() {
//        for (Condition input : inputs) {
//            if (!input.evaluate()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean evaluateCondition(int conditionIndex, float sensorVal) {
        Condition condition = inputs.get(conditionIndex);
        return condition.evaluate(sensorVal);
    }

    public void triggerOutput(Context appContext) {
        output.onTrigger(appContext);
        isOn = !isOn;
    }

    public void setInput(int index, Condition condition) {
        // TODO: Add the ability to update multiple conditions in input
        // TODO: Maybe just change condition param to ArrayList<Condition>; check Serializability
        // REALLY SUBOPTIMAL
        if (inputs.size() > index) {
            inputs.set(index, condition);
        } else {
            inputs.add(condition);
        }
    }

    public ArrayList<Condition> getInput() {
        return inputs;
    }

    public void setOutput(Output newOutput) {
        output = newOutput;
    }

    public Output getOutput() {
        return output;
    }
}
