package com.example.courseregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.courseregistration.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Button loginBtn;
    CheckBox rememberChb;
    EditText usernameEd, passwordEd;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        loginBtn = findViewById(R.id.loginBtn);
        rememberChb = findViewById(R.id.rememberChb);
        usernameEd = findViewById(R.id.usernameEd);
        passwordEd = findViewById(R.id.passwordEd);

        sharedPreferences = getSharedPreferences("login_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        usernameEd.setText(username);
        passwordEd.setText(password);

        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        rememberChb.setChecked(rememberMe);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(usernameEd.getText().toString())) {
                    usernameEd.setError("Please enter your username!");
                } else if (TextUtils.isEmpty(passwordEd.getText().toString())) {
                    passwordEd.setError("Please enter your password!");
                }
                else {
                    String username = usernameEd.getText().toString();
                    String password = passwordEd.getText().toString();

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

                    String url = "http://10.0.2.2/university/get_student.php?username=" + username;
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals(password)){
                                        Intent i = new Intent(MainActivity.this, RegisteredCoursesActivity.class);
                                        i.putExtra("username", username);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Invalid username or password!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void
                                onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(request);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create) {
            Intent i = new Intent(MainActivity.this, AccountRegisterActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}