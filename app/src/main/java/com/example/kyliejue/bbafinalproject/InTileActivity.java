package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class InTileActivity extends AppCompatActivity {

    // TODO: Move these to a res.values file
    private static final int LIGHT_CODE = 2;
    private static final int MAGNET_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_tile);
    }

    public void openLightSensor(View view) {
        Condition condition = new Condition();
        Intent intent = new Intent(this, LightActivity.class);
        if (intent.getSerializableExtra("domino_input") != null) {
            condition = (Condition) intent.getSerializableExtra("domino_input");
            intent.putExtra("domino_input", condition);
        }
        startActivityForResult(intent, LIGHT_CODE);
        Log.d("STATE", "WAITING FOR LIGHT");
    }

    public void openMagnetometer(View view) {
        Condition condition = new Condition();
        Intent intent = new Intent(this, MagnetometerActivity.class);
        if (intent.getSerializableExtra("domino_input") != null) {
            condition = (Condition) intent.getSerializableExtra("domino_input");
            intent.putExtra("domino_input", condition);
        }
        startActivityForResult(intent, MAGNET_CODE);
        Log.d("STATE", "WAITING FOR MAGNETS");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LIGHT_CODE) { //|| requestCode == MAGNET_CODE) {
            Intent intent = new Intent();
            Condition condition = (Condition) data.getSerializableExtra("update_input");
            Log.d("STATE", "Created Condition with op: " + condition.getOp());
            Log.d("STATE", "Created Condition with chosenVal: " + condition.getChosenValue());
            Log.d("STATE", "Created Condition with sensor: "+ Double.toString(condition.getSensor()));

            intent.putExtra("update_input", condition);
            setResult(RESULT_OK, intent);
            finish();
            Log.d("STATE", "TESTING INTILE");
        }
    }
}
