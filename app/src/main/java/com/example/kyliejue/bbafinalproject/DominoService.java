package com.example.kyliejue.bbafinalproject;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

public class DominoService extends IntentService {

    // Map of domino names to domino objects
    HashMap<String, Domino> dominoes;
    // May be more efficient to store on/off information in a data structure here rather than in the
    // Domino objects to prevent needing to loop through all dominoes in the arraylist

    public DominoService() {
        super("DominoService");
        dominoes = new HashMap<String, Domino>(); // Should this go in the constructor?
        Domino testDom = new Domino();
        dominoes.put("testDominoName", testDom);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new Thread(new Runnable(){
            public void run() {
                while(true) {
                    for (Domino domino : dominoes.values()) {
                        if (domino.isOn() && domino.conditionsSatisfied()) {
                            domino.triggerOutput();
                        }
                    }
                }

            }
        }).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
        //return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO: Check if this code should be moved to a different method
        Log.d("STATE", "Domino service called");

        String action = intent.getAction();
        String name = intent.getStringExtra("name");
        if (action.equals("getDomino")) {
            Log.d("STATE", "getDomino action");
            Intent returnDomino = new Intent();
            returnDomino.setAction("getDomino");
            returnDomino.putExtra("return_domino", dominoes.get(name));
            sendBroadcast(returnDomino);
        }
        if (action.equals("updateDomino")) {
            Domino updatedDomino = (Domino) intent.getSerializableExtra("updated_domino");
            dominoes.put(name, updatedDomino);
        }
        if (action.equals("toggleDomino")) {
            dominoes.get(name).toggle();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
