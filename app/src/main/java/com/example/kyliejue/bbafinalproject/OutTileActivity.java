package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class OutTileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_tile);

    }


    public void onFlashlight(View view){
        Intent flashlightIntent = new Intent(this, OutputFlashlightActivity.class);
        startActivityForResult(flashlightIntent, 1);
        setContentView(R.layout.activity_output_flashlight);

    }


    public void onSound(View view){
        Intent intent = new Intent(this, OutputSoundActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_output_flashlight);
    }

    //when I receive object from the specific outTileActivity pages...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent requestIntent = getIntent();
        Output outputObjectFromDomino = (Output) requestIntent.getSerializableExtra("domino_output");
        Log.d("STATE", "does it ever get here?");
        if(resultCode != RESULT_CANCELED) {
            Log.d("STATE", "section for result code = 1");
            Output outputObject = (Output) data.getSerializableExtra("outputObject");
            Log.d("STATE", "hello just checking if this onActivityResult runs");
            Log.d("STATE", outputObject.toString());
            outputObjectFromDomino = outputObject;
            Log.d("STATE", outputObjectFromDomino.toString());

//                outputObjectFromDomino.onTrigger();
        }
    }
}
