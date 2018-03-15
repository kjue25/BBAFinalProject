package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements SensorEventListener {

    private Domino editDomino;

    // Request codes for OutTile and InTile activities
    // TODO: Move these to a res.values file
    private static final int INTILE_CODE = 0;
    private static final int OUTTILE_CODE = 1;

    // This sensor code only works for our current prototype (only works if one sensor is active)
    SensorManager sensorManager;
    Sensor sensor;
    float currSensorValue;


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.d("STATE", "sensor changing");
        currSensorValue = sensorEvent.values[0];
        while (editDomino.isOn()) {
            evaluate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

// TODO: Restore if convert to Parceable
//    private class DominoReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d("STATE", "onReceive");
//            String action = intent.getAction();
//            if (action.equals("getDomino")) {
//                editDomino = (Domino) intent.getSerializableExtra("return_domino");
//                Log.d("STATE", "RECEIVED DOMINO with isOn: " + Boolean.toString(editDomino.isOn())); //SHOULD BE FALSE
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currSensorValue = 0;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        Intent parentIntent = getIntent();
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editDomino = new Domino();

        Switch turnOn = findViewById(R.id.active_toggle);
        turnOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editDomino.toggle();
            }
        });

        // TODO: Determine if starting service needs to happen in the main activity
//        Intent intent = new Intent(this, DominoService.class);
//        intent.setAction("getDomino");
//        intent.putExtra("name", parentIntent.getStringExtra("domino_name"));
//        startService(intent);
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("getDomino");
//        registerReceiver(new DominoReceiver(), filter);
//        // Without unregisterReceiver, back button from this page causes crash
//
//        Log.d("STATE", "Finished registering domino receiver");
    }

    @Override
    protected void onStop() {
        // TODO: unregister BroadcastReceivers here?
        super.onStop();
    }


    public void openInTilePage(View view) {
        Intent intent = new Intent(this, InTileActivity.class);
        //TODO: Fix the following line to handle ArrayList<Condition>
        if (!editDomino.getInput().isEmpty()) {
            intent.putExtra("domino_input", editDomino.getInput().get(0));
        }
        startActivityForResult(intent, INTILE_CODE);
        Log.d("STATE", "WAITING FOR INTILE");
    }

    //Navigates to the OutTile creation page
    public void openOutTilePage(View view) {
        Intent intent = new Intent(this, OutTileActivity.class);
        if(editDomino.getOutput() != null){
            intent.putExtra("domino_output", editDomino.getOutput()); // TODO: Make sure this can handle null
        }
        startActivityForResult(intent, OUTTILE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("STATE", "RECEIVED RESULT");

        if (requestCode == INTILE_CODE) {
            // TODO: Get index from data as well when >1 condition allowed
            Condition condition = (Condition) data.getSerializableExtra("update_input");
            editDomino.setInput(0, condition);
            sensor = sensorManager.getDefaultSensor(condition.getSensor());
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

            Log.d("STATE", Double.toString(editDomino.getInput().get(0).getSensor()));
        } else if (requestCode == OUTTILE_CODE) {
            // Get output object from data.getExtras();
            Output output = (Output) data.getSerializableExtra("update_output");
            Log.d("STATE", "Created Output with duration: " + ((OutputFlashlight) output).getDuration());
            editDomino.setOutput(output);

            // Show evaluate switch
            Switch turnOn = findViewById(R.id.active_toggle);
            if (turnOn != null) {
                turnOn.setVisibility(View.VISIBLE);
            }
        }
    }

    private void evaluate() {
        Log.d("STATE", "Domino is ON");
        int index = 0;
        for (Condition condition : editDomino.getInput()) {
            if (!editDomino.evaluateCondition(index, currSensorValue)) {
                return;
            }
            index++;
        }
        Log.d("STATE", "Domino is running!");
        editDomino.triggerOutput(getApplicationContext());
        editDomino.toggle();
    }

}