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


    // Request codes for OutTile and InTile activities
    // TODO: Move these to a res.values file
    private static final int RECEIVED_INTILE_CODE = 0;
    private static final int RECEIVED_OUTTILE_CODE = 1;
    private static final int EDIT_INTILE_CODE = 2;
    private static final int EDIT_OUTTILE_CODE = 3;

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
        Intent intent = new Intent(this, InTileActivity.class);
        //TODO: Fix the following line to handle ArrayList<Condition>
        intent.putExtra("domino_input", editDomino.getInput().get(0));
        startActivityForResult(intent, EDIT_INTILE_CODE);
        setContentView(R.layout.activity_in_tile);
    }

    //Navigates to the OutTile creation page
    public void openOutTilePage(View view) {
        // TODO: Replace SensorActivity.class with OutTileActivity.class
        Intent intent = new Intent(this, SensorActivity.class);
        intent.putExtra("domino_output", editDomino.getOutput());
        startActivityForResult(intent, EDIT_OUTTILE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECEIVED_INTILE_CODE) {
            // TODO: Get index from data as well
            Condition condition = (Condition) data.getSerializableExtra("update_input");
            editDomino.setInput(0, condition);
        } else if (requestCode == RECEIVED_OUTTILE_CODE) {
            // Get output object from data.getExtras();
            Output output = (Output) data.getSerializableExtra("update_output");
            editDomino.setOutput(output);
        }
    }

}

