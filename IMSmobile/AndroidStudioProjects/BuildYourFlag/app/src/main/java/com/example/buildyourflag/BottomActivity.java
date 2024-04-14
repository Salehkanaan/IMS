package com.example.buildyourflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class BottomActivity extends AppCompatActivity {

    SharedPreferences sp;
    RadioButton blueRb, redRb, greenRb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        blueRb = findViewById(R.id.blueRb);
        redRb = findViewById(R.id.redRb);
        greenRb = findViewById(R.id.greenRb);
        sp = getSharedPreferences("colors", MODE_PRIVATE);
    }

    public void bottomSetButton(View v){
        String color;
        if(blueRb.isChecked())
            color = "blue";
        else if(redRb.isChecked())
            color = "red";
        else
            color = "green";

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("bottom_color", color);
        editor.commit();
        finish();
    }

}