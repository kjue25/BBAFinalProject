package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OutputSoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_sound);
        Button playButton = (Button) findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(OutputSoundActivity.this, R.raw.happy );
                mediaPlayer.start(); // no need to call prepare(); create() does that for you

                //create outputFlashlight object
//                Output outputFlashlight = new OutputFlashlight(duration, hasCameraFlash, OutputFlashlightActivity.this);
//                outputFlashlight.onTrigger();
            }
        });

    }
}
