package com.example.buildyourflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class TopActivity extends AppCompatActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        sp = getSharedPreferences("colors", MODE_PRIVATE);

    }

    public void redButtonFunction(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("top_color", "red");
        editor.commit();
        finish();
    }

    public void blueButtonFunction(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("top_color", "blue");
        editor.commit();
        finish();
    }

    public void greenButtonFunction(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("top_color", "green");
        editor.commit();
        finish();
    }
}