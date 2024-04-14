package com.example.buildyourflag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button topButton, middleButton, bottomButton;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topButton = findViewById(R.id.topButton);
        middleButton = findViewById(R.id.middleButton);
        bottomButton = findViewById(R.id.bottomButton);

        sp = getSharedPreferences("colors", MODE_PRIVATE);
    }

    public void topButtonFunction(View v){
        Intent i = new Intent(this, TopActivity.class);
        startActivity(i);
    }

    public void middleButtonFunction(View v){
        Intent i = new Intent(this, MiddleActivity.class);
        startActivity(i);
    }

    public void bottomButtonFunction(View v){
        Intent i = new Intent(this, BottomActivity.class);
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        String topColor = sp.getString("top_color", "");
        if(!TextUtils.isEmpty(topColor)){
            topButton.setText("");
            if(topColor.equals("red")){
                topButton.setBackgroundColor(Color.RED);
            }
            else if(topColor.equals("blue")){
                topButton.setBackgroundColor(Color.BLUE);
            }
            else if(topColor.equals("green")){
                topButton.setBackgroundColor(Color.GREEN);
            }
        }

        String middleColor = sp.getString("middle_color", "");
        if(!TextUtils.isEmpty(middleColor)){
            middleButton.setText("");
            if(middleColor.equals("white")){
                middleButton.setBackgroundColor(Color.WHITE);
            }
            else if(middleColor.equals("green")){
                middleButton.setBackgroundColor(Color.GREEN);
            }
            else if(middleColor.equals("yellow")){
                middleButton.setBackgroundColor(Color.YELLOW);
            }
        }

        String bottomColor = sp.getString("bottom_color", "");
        if(!TextUtils.isEmpty(bottomColor)){
            bottomButton.setText("");
            if(bottomColor.equals("red")){
                bottomButton.setBackgroundColor(Color.RED);
            }
            else if(bottomColor.equals("blue")){
                bottomButton.setBackgroundColor(Color.BLUE);
            }
            else if(bottomColor.equals("green")){
                bottomButton.setBackgroundColor(Color.GREEN);
            }
        }
    }
}