package com.example.kyliejue.bbafinalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    private Domino editDomino;
    private DominoReceiver dominoReceiver;

    private class DominoReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("returnDomino")) {
                editDomino = (Domino) intent.getSerializableExtra("return_domino");
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

        Intent intent = new Intent(this, DominoService.class);
        intent.setAction("getDomino");
        intent.putExtra("name", parentIntent.getStringExtra("domino_name"));
        startService(intent);

        IntentFilter filter = new IntentFilter();
        filter.addAction("getDomino");
        dominoReceiver = new DominoReceiver();
        registerReceiver(dominoReceiver, filter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(dominoReceiver);
        super.onStop();
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
