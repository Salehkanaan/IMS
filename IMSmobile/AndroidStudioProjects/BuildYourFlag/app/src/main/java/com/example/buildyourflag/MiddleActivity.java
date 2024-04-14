package com.example.buildyourflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MiddleActivity extends AppCompatActivity {

    Spinner colorSpinner;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        colorSpinner = findViewById(R.id.colorSpinner);
        sp = getSharedPreferences("colors", MODE_PRIVATE);
    }

    public void middleSetButton(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("middle_color", colorSpinner.getSelectedItem().toString());
        editor.commit();
        finish();
    }

}