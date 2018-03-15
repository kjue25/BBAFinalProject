package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class InTileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_tile);

        // TODO: Move this to happen after tile is actually chosen
        Intent intent = new Intent();
        Condition testCondition = new Condition(Sensor.TYPE_LIGHT, "=", "10");
        Log.d("STATE", "Created Condition with op: " + testCondition.getOp());
        Log.d("STATE", "Created Condition with chosenVal: " + testCondition.getChosenValue());
        Log.d("STATE", "Created Condition with sensor: "+ Double.toString(testCondition.getSensor()));

        intent.putExtra("update_input", testCondition);
        setResult(RESULT_OK, intent);
        finish();
        Log.d("STATE", "TESTING INTILE");

    }
}
