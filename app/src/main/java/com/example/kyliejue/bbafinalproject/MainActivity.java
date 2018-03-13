package com.example.kyliejue.bbafinalproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openEditPage(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_edit);
    }

}
