package com.example.imsstock;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
Button btn;
EditText name,pass;
TextView log;
CheckBox rememberChb;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    private static final String TAG = "PushNotification";
    private static final String CHANNEL_ID = "101";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
log=findViewById(R.id.log);
        btn=findViewById(R.id.Sub);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        rememberChb = findViewById(R.id.rememberChb);
        getToken();
        createNotificationChannel();

        sharedPreferences = getSharedPreferences("login_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        name.setText(username);
        pass.setText(password);

        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        rememberChb.setChecked(rememberMe);
log.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        log.setText("**Contact support!**");
        log.setTextColor(Color.RED);
    }
});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Please enter your username!");
                } else if (TextUtils.isEmpty(pass.getText().toString())) {
                    pass.setError("Please enter your password!");
                }
                else {
                    String username = name.getText().toString();
                    String password = pass.getText().toString();

                    // saving data to the file
                    if (rememberChb.isChecked()){
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putBoolean("rememberMe", true);
                        editor.commit();
                    }
                    else {
                        editor.putString("username", "");
                        editor.putString("password", "");
                        editor.putBoolean("rememberMe", false);
                        editor.commit();
                    }
                    String timeformat="MM/dd/yy";
                    Date d= Calendar.getInstance().getTime();
                    String date=DateFormat.format(timeformat,d).toString();
                        String url = "http://192.168.184.115/login.php?name=" +username + "&pass=" + password ;
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        StringRequest request = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (!response.equals("-1")) {
                                            updtDate(date, response);
                                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                            i.putExtra("username",username);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void
                                    onErrorResponse(VolleyError error) {
                                        Toast.makeText(LoginActivity.this, "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        queue.add(request);
                }
            }
        });
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG,"Subscribe failed");

                        }
                        String token =task.getResult();
                        Log.d(TAG,token);
                    }
                });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotifChannel";
            String description = "Recieve Firebase notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void updtDate (String date,String nam){

        String url =  "http://192.168.184.115/updtdate.php?date=" +date+"&name="+nam;
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
