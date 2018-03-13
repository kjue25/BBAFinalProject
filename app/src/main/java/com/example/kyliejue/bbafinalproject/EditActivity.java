package com.example.kyliejue.bbafinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openInTilePage(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_sensor);
    }

    //Should navigate to the OutTile creation page
    public void openOutTilePage(View view) {
        // Need to add intent and call to setContentView here
    }

}
