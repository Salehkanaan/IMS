package com.example.restaurant;

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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restaurant.databinding.ActivityAddBinding;

import java.lang.reflect.Method;

public class AddActivity extends AppCompatActivity {

    EditText nameEd, priceEd;
    Spinner typeSpinner;
    ProgressBar pg;

    private AppBarConfiguration appBarConfiguration;
    private ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameEd = findViewById(R.id.nameEd);
        priceEd = findViewById(R.id.priceEd);
        typeSpinner = findViewById(R.id.typeSpinner);
        pg = findViewById(R.id.progressBar);

        pg.setVisibility(View.INVISIBLE);

        setSupportActionBar(binding.toolbar);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            pg.setVisibility(View.VISIBLE);
            String name = nameEd.getText().toString();
            String price = priceEd.getText().toString();
            String type = typeSpinner.getSelectedItem().toString();

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://10.0.2.2/restaurant/adddish.php?name=" + name + "&price=" + price + "&type=" + type;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(AddActivity.this, response, Toast.LENGTH_SHORT).show();
                    pg.setVisibility(View.INVISIBLE);
                        if(!response.equals("-1")){
                            finish();
                        }
                        else{
                            Toast.makeText(AddActivity.this, "No dish is added!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    });
            queue.add(stringRequest);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}