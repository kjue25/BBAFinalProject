package com.example.kyliejue.bbafinalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    private Domino editDomino;
    private DominoReceiver dominoReceiver;

    private class DominoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("STATE", "onReceive");
            String action = intent.getAction();
            if (action.equals("getDomino")) {
                editDomino = (Domino) intent.getSerializableExtra("return_domino");
                Log.d("STATE", "RECEIVED DOMINO with isOn: " + Boolean.toString(editDomino.isOn())); //SHOULD BE FALSE
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent parentIntent = getIntent();
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: Determine if starting service needs to happen in the main activity
        Intent intent = new Intent(this, DominoService.class);
        intent.setAction("getDomino");
        intent.putExtra("name", parentIntent.getStringExtra("domino_name"));
        startService(intent);

        IntentFilter filter = new IntentFilter();
        filter.addAction("getDomino");
        registerReceiver(new DominoReceiver(), filter);
        // Without unregisterReceiver, back button from this page causes crash

        Log.d("STATE", "Finished registering domino receiver");
    }

    @Override
    protected void onStop() {
        // TODO: unregister BroadcastReceivers here?
        super.onStop();
    }

    public void openInTilePage(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_sensor);
    }

    //Should navigate to the OutTile creation page
    public void openOutTilePage(View view) {
        //TODO: Add intent and call to setContentView here
    }

}

