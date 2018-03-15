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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_discover:

                                break;
                            case R.id.action_challenges:

                                break;
                            case R.id.action_create:

                                break;
                        }
                        return false;
                    }
                });
    }

    public void openEditPage(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        // TODO: Get domino name to pass into intent
        intent.putExtra("domino_name", "testDominoName");
        startActivity(intent);
        setContentView(R.layout.activity_edit);
    }


}
