package com.example.kyliejue.bbafinalproject;


import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.support.annotation.NonNull;



//BACK BUTTON CURRENTLY BROKEN EVERYWHERE...
public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openEditPage(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        // TODO: Get domino name to pass into intent
        intent.putExtra("domino_name", "testDominoName");
        startActivity(intent);
        setContentView(R.layout.activity_edit);
    }



}
