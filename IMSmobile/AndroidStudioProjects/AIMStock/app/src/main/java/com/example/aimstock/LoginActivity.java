package com.example.aimstock;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
Button b;
TextView tv;
EditText name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        b=findViewById(R.id.Sub);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
    }
    public void Submit(View v){
        String timeformat="MM/dd";
       Date d= Calendar.getInstance().getTime();
String date=DateFormat.format(timeformat,d).toString();
        String nam=name.getText().toString();
        String pass1=pass.getText().toString();
        if(!nam.equals("")&&!pass1.equals("")){
            String url = "http://192.168.54.115/login.php?name=" +name + "&pass=" + pass1 ;
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(
                    Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            if(!s.equals("-1")){
                                updtDate(date,nam);
                                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Wrong name or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String errorMessage = "Error: ";
                            errorMessage += error.toString();
                            Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                        }
                    });
            queue.add(request);}
        else{
            Toast.makeText(LoginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        }

    public void updtDate (String date,String nam){

        String url =  "http://192.168.54.115/updtdate.php?date=" +date+"&name="+nam;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        if(!s.equals("-1")){
                            Toast.makeText(LoginActivity.this, "Welcome "+nam, Toast.LENGTH_SHORT).show();
                        }
                        else{
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error: ";
                        errorMessage += error.toString();
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    }
