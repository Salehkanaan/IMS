package com.example.caloriyat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.resultv);

        Intent j=getIntent();
      Double d=  j.getDoubleExtra("d",-1);
        textView.setText("You need to eat "+d+" calories per day");
    }
}