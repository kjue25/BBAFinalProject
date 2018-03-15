package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class OutTileActivity extends AppCompatActivity {

    private static final int FLASHLIGHT_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_tile);

    }


    public void onFlashlight(View view){
        Intent flashlightIntent = new Intent(this, OutputFlashlightActivity.class);
        if (flashlightIntent.getSerializableExtra("domino_output") != null) {
            Output output = (Output) flashlightIntent.getSerializableExtra("domino_output");
            flashlightIntent.putExtra("domino_output", flashlightIntent);
        }
        startActivityForResult(flashlightIntent, FLASHLIGHT_CODE);
        Log.d("STATE", "WAITING FOR FLASHLIGHT");
    }


    public void onSound(View view){
        Intent intent = new Intent(this, OutputSoundActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_output_flashlight);
    }

    //when I receive object from the specific outTileActivity pages...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Intent from EditActivity
        Intent requestIntent = getIntent();
        Output outputObject = (Output) requestIntent.getSerializableExtra("domino_output");

        // Intent to return to EditActivity
        Intent intent = new Intent();
        if (requestCode == FLASHLIGHT_CODE) {
            Log.d("STATE", "Received Flashlight output");
            outputObject = (Output) data.getSerializableExtra("update_output");
            Log.d("STATE", "Created Output with duration: " + ((OutputFlashlight) outputObject).getDuration());
        }

        intent.putExtra("update_input", outputObject);
        setResult(RESULT_OK, intent);
        finish();
        Log.d("STATE", "TESTING OUTTILE");
    }
}
