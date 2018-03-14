package com.example.kyliejue.bbafinalproject;

/**
 * Created by aashnashroff on 3/13/18.
 */

public class OutputFlashlight implements Output {
    private int duration;

    public void OutputFlashlight(int duration) {
        this.duration = duration;
    }

    @Override
    public void onTrigger() {

    }
}
