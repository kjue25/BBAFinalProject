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

public class EditActivity extends AppCompatActivity {

    private Domino editDomino;
    private Thread evalThread;

    // Request codes for OutTile and InTile activities
    // TODO: Move these to a res.values file
    private static final int INTILE_CODE = 0;
    private static final int OUTTILE_CODE = 1;

    private class RawSensorListener implements SensorEventListener {

        private SensorManager sensorManager;
        private Sensor sensor;
        private float currValue;

        public RawSensorListener(int sensorType) {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorManager.getDefaultSensor(sensorType);
            currValue = 0;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            currValue = sensorEvent.values[0];
        }

        public float getCurrValue() {
            return currValue;
        }
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
        Intent parentIntent = getIntent();
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editDomino = new Domino();

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

    //Navigates to the OutTile creation page
    public void openOutTilePage(View view) {
        // TODO: Replace SensorActivity.class with OutTileActivity.class
        Intent intent = new Intent(this, OutTileActivity.class);
        if(editDomino.getOutput() != null){
            intent.putExtra("domino_output", editDomino.getOutput()); // TODO: Make sure this can handle null
        }
        startActivityForResult(intent, OUTTILE_CODE);
        setContentView(R.layout.activity_out_tile);
    }

    public void openInTilePage(View view) {
        Intent intent = new Intent(this, InTileActivity.class);
        //TODO: Fix the following line to handle ArrayList<Condition>
        if (!editDomino.getInput().isEmpty()) {
            intent.putExtra("domino_input", editDomino.getInput().get(0));
        }
        startActivityForResult(intent, INTILE_CODE);
        setContentView(R.layout.activity_in_tile);
        Log.d("STATE", "WAITING FOR INTILE");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("STATE", "RECEIVED RESULT");

        if (requestCode == INTILE_CODE) {
            // TODO: Get index from data as well when >1 condition allowed
            Condition condition = (Condition) data.getSerializableExtra("update_input");
            editDomino.setInput(0, condition);
            Log.d("STATE", Double.toString(editDomino.getInput().get(0).getSensor()));
        } else if (requestCode == OUTTILE_CODE) {
            // Get output object from data.getExtras();
            Output output = (Output) data.getSerializableExtra("update_output");
            editDomino.setOutput(output);

            // Show evaluate switch
            Switch turnOn = findViewById(R.id.active_toggle);
            if (turnOn != null) {
                turnOn.setVisibility(View.VISIBLE);
                turnOn.setOnCheckedChangeListener(null);
            }
        }
    }

    // TODO: NEED TO TEST
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            editDomino.toggle();
            evalThread = new Thread(new Runnable() {
                public void run() {
                    while (true) { //FIXME ?
                        evaluate();
                    }
                }
            });
           evalThread.start();
        } else {
            if (evalThread != null) {
                evalThread.interrupt();
            }
        }
    }

    private void evaluate() {
        if (editDomino.isOn()) {
            int index = 0;
            for (Condition condition : editDomino.getInput()) {
                RawSensorListener listener = new RawSensorListener(condition.getSensor());
                if (!editDomino.evaluateCondition(index, listener.getCurrValue())) {
                    return;
                }
                index++;
            }
            editDomino.triggerOutput(getApplicationContext());
            editDomino.toggle();
        }
    }

}