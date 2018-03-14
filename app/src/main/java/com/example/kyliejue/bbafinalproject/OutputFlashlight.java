package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;

/**
 * Created by aashnashroff on 3/13/18.
 */

public class OutputFlashlight implements Output {
    private int duration;
    private CameraManager cameraManager;
    private boolean hasCameraFlash;
    private boolean flashLightStatus = false;

    public OutputFlashlight(int duration, CameraManager cameraManager, boolean hasCameraFlash) {
        this.duration = duration;
        this.cameraManager = cameraManager;
        this.hasCameraFlash = hasCameraFlash;
    }

    @Override
    public void onTrigger() {
        if (this.hasCameraFlash) {
            if (this.flashLightStatus)
                flashLightOff();
            else
                flashLightOn();
        } else {
            //TO DO
//            Toast.makeText(OutputFlashlightActivity.this, "No flash available on your device",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    private void flashLightOn() {
        long blinkDelay = 500; //Delay in ms
        long startTime = System.currentTimeMillis(); //fetch starting time

        boolean currentBlinkStatus = false;

        try {
            String cameraId = this.cameraManager.getCameraIdList()[0];
            while((System.currentTimeMillis() - startTime) < this.duration){
                if(!currentBlinkStatus) {
                    this.cameraManager.setTorchMode(cameraId, true);
                    currentBlinkStatus = true;
                }
                else {
                    this.cameraManager.setTorchMode(cameraId, false);
                    currentBlinkStatus = false;
                }
                try {
                    Thread.sleep(blinkDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                this.flashLightStatus = true;
            }
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        try {
            String cameraId = this.cameraManager.getCameraIdList()[0];
            this.cameraManager.setTorchMode(cameraId, false);
            this.flashLightStatus = false;
        } catch (CameraAccessException e) {
        }
    }

}
