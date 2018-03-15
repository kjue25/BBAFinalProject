package com.example.kyliejue.bbafinalproject;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OutputFlashlightActivity extends AppCompatActivity {

    private Button enableButton;
    private Button turnOnButton;
    private static final int CAMERA_REQUEST = 50;
    private boolean sensorCondition = true;
    private boolean flashLightStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_flashlight);
        Button turnOnButton = (Button) findViewById(R.id.turnOn);
        Button enableButton = (Button) findViewById(R.id.enable);
        final EditText editText = (EditText) findViewById(R.id.durationTextBox);

        final boolean hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;


        enableButton.setEnabled(!isEnabled);
        turnOnButton.setEnabled(isEnabled);
        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(OutputFlashlightActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
            }
        });

        turnOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duration = Integer.parseInt(editText.getText().toString())*1000;
                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

                //create outputFlashlight object
                Output outputFlashlight = new OutputFlashlight(duration, hasCameraFlash);
                Intent outputIntent = new Intent();
                outputIntent.putExtra("outputObject", outputFlashlight);
                setResult(1, outputIntent);


                Log.d("STATE", "now sending the flashlight object to outtile activity");
                outputFlashlight.onTrigger(getApplicationContext());

                finish();
            }
        });
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
