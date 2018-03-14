package com.example.kyliejue.bbafinalproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//BACK BUTTON CURRENTLY BROKEN EVERYWHERE...
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, DominoService.class);
//        startService(intent);
    }

    public void openEditPage(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        // TODO: Get domino name to pass into intent
        intent.putExtra("domino_name", "testDominoName");
        startActivity(intent);
        setContentView(R.layout.activity_edit);
    }

    //stopService() ???

}
