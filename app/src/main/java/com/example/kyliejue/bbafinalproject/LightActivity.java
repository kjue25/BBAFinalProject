package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LightActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String operand;
    private String chosenVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        Spinner spinner = findViewById(R.id.operand_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operand_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        operand = parent.getItemAtPosition(pos).toString();
        Spinner spinner = findViewById(R.id.operand_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        operand = "=";
    }

    public void setLightSensor(View view) {
        Intent intent = new Intent();
        Condition lightCondition = new Condition(Sensor.TYPE_LIGHT, operand, chosenVal);
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
