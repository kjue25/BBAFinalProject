package com.example.kyliejue.bbafinalproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OutputFlashlightActivity extends AppCompatActivity {

    private Button enableButton;
    private Button turnOnButton;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;
    private boolean sensorCondition = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_flashlight);
        Button turnOnButton = (Button) findViewById(R.id.turnOn);
        Button enableButton = (Button) findViewById(R.id.enable);

        final boolean hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;


//        Log.d("STATE", "hello");
//        Log.d("STATE", "" + turnOnButton);
//        Log.d("STATE", "" + enableButton);
//        Log.d("STATE", ""+isEnabled);

        enableButton.setEnabled(!isEnabled);
        turnOnButton.setEnabled(isEnabled);

        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("STATE", "hello");

                ActivityCompat.requestPermissions(OutputFlashlightActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
            }
        });

        turnOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create outputFlashlight object
                Output outputFlashlight = new OutputFlashlight();
                if (hasCameraFlash) {
                    if (flashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(OutputFlashlightActivity.this, "No flash available on your device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        long blinkDelay = 2000; //Delay in ms
        boolean currentBlinkStatus = false;
        String myString = "0101010101";

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            for (int i = 0; i < myString.length(); i++) {
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
                flashLightStatus = true;
            }
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
        } catch (CameraAccessException e) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST :
                if (grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableButton.setEnabled(false);
                    enableButton.setText("Camera Enabled");
                    turnOnButton.setEnabled(true);
                } else {
                    Toast.makeText(OutputFlashlightActivity.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
