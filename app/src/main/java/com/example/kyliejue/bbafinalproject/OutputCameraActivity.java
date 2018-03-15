package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OutputCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_camera);
        Button clickButton = (Button) findViewById(R.id.clickButton);
        final ImageView mImageView = (ImageView) findViewById(R.id.mImageView);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create OutputCamera object
                Output outputCamera = new OutputCamera(OutputCameraActivity.this, mImageView);
                outputCamera.onTrigger(getApplicationContext());
            }
        });
    }
}
