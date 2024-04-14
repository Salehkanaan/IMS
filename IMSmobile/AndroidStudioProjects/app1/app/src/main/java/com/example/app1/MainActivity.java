package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   EditText ed1,ed2;
   TextView nb1,nb2,resTv;
  Button subtractButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.nb1);
        ed2=findViewById(R.id.nb2);
        resTv=findViewById(R.id.resTv);
        subtractButton=findViewById(R.id.subButton);

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nb1=Integer.parseInt(ed1.getText().toString());
                int nb2=Integer.parseInt(ed2.getText().toString());
                int res=nb1-nb2;
                resTv.setText(res+"");
            }
        });

    }
    public void addFunction(View v){

        int nb1=Integer.parseInt(ed1.getText().toString());
        int nb2=Integer.parseInt(ed2.getText().toString());
        int res=nb1+nb2;
        resTv.setText(res+"");

    }

}