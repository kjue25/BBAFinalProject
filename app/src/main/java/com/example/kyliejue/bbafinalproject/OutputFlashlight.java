package com.example.kyliejue.bbafinalproject;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;

/**
 * Created by aashnashroff on 3/13/18.
 * TO DO: check if the camera manager can be extracted from this class instead of passing it here from the activity page.
 */

public class OutputFlashlight implements Output {
    private int duration;
    private boolean hasCameraFlash;
//    private OutputFlashlight context;

    public OutputFlashlight(int duration, boolean hasCameraFlash ) {
        this.duration = duration;
        this.hasCameraFlash = hasCameraFlash;
//        this.context = this;
    }

    @Override
    public void onTrigger(Context context) {
        if (this.hasCameraFlash) {
                flashLightOn(context);
        } else {
            //TO DO
//            Toast.makeText(this.context, "No flash available on your device",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    private void flashLightOn(Context context) {
        long blinkDelay = 500; //Delay in ms
        long startTime = System.currentTimeMillis(); //fetch starting time
        boolean currentBlinkStatus = false;
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);


        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            while((System.currentTimeMillis() - startTime) < this.duration){
                if(!currentBlinkStatus) {
                    cameraManager.setTorchMode(cameraId, true);
                    currentBlinkStatus = true;
                }
                else {
                    cameraManager.setTorchMode(cameraId, false);
                    currentBlinkStatus = false;
                }
                try {
                    Thread.sleep(blinkDelay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        } catch (CameraAccessException e) {
        }
    }

//    private void flashLightOff() {
//        try {
//            CameraManager cameraManager = (CameraManager) this.context.getSystemService(Context.CAMERA_SERVICE);
//            String cameraId = cameraManager.getCameraIdList()[0];
//            cameraManager.setTorchMode(cameraId, false);
//            this.flashLightStatus = false;
//        } catch (CameraAccessException e) {
//        }
//    }

}
