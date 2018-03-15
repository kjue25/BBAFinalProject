package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
    }

    public void setLightSensor(View view) {
        Intent intent = new Intent();
        Condition lightCondition = new Condition(Sensor.TYPE_LIGHT, "=", "10");
        Log.d("STATE", "Created Condition with op: " + lightCondition.getOp());
        Log.d("STATE", "Created Condition with chosenVal: " + lightCondition.getChosenValue());
        Log.d("STATE", "Created Condition with sensor: "+ Double.toString(lightCondition.getSensor()));

        intent.putExtra("update_input", lightCondition);
        setResult(RESULT_OK, intent);
        finish();
        Log.d("STATE", "TESTING LIGHT");
    }

    // set properties of condition and return in
}
