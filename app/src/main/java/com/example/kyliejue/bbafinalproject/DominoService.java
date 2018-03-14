package com.example.kyliejue.bbafinalproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DominoService extends Service {

    // Map of domino names to domino objects
    HashMap<String, Domino> dominoes;
    // May be more efficient to store on/off information in a data structure here rather than in the
    // Domino objects to prevent needing to loop through all dominoes in the arraylist

    public DominoService() {
        dominoes = new HashMap<String, Domino>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Domino getDomino(String name){
        return dominoes.get(name);
    }

    public void updateDomino(String name, Domino domino) {
        dominoes.put(name, domino);
    }

    public void toggleDomino(String name) {
        dominoes.get(name).toggle();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
//                    Thread.sleep(60000)
                        //ADD CODE
                }

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize dominoes
    }
}
