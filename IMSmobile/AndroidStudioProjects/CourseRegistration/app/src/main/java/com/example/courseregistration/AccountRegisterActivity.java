package com.example.courseregistration;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.courseregistration.databinding.ActivityAccountRegisterBinding;

public class AccountRegisterActivity extends AppCompatActivity {
    EditText usernameEd, passEd, emailEd;
    RadioButton frb, mrb;
    private AppBarConfiguration appBarConfiguration;
    private ActivityAccountRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        usernameEd = findViewById(R.id.registerNameEd);
        passEd = findViewById(R.id.registerPassEd);
        emailEd = findViewById(R.id.emailEd);

        frb = findViewById(R.id.femaleRb);
        mrb = findViewById(R.id.maleRb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_register, menu);
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
            String username = usernameEd.getText().toString();
            String password = passEd.getText().toString();
            String email = emailEd.getText().toString();
            String gender = "";
            if (frb.isChecked())
                gender = frb.getText().toString();
            else
                gender = mrb.getText().toString();

            if (username.equals("") || password.equals("") || email.equals("")) {
                Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
            } else {
                String url = "http://10.0.2.2/university/add_student.php?username=" + username +
                        "&password=" + password + "&email=" + email + "&gender=" + gender;
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_LONG).show();
                                finish();
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}