package com.example.courseregistration;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.courseregistration.databinding.ActivityUniversityCoursesBinding;

import org.json.JSONArray;

public class UniversityCoursesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityUniversityCoursesBinding binding;
    String username;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUniversityCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Intent i = getIntent();
        username = i.getStringExtra("username");

        listView = findViewById(R.id.uniCoursesListView);

        getAllRegisteredCoursesFromDB();

    }


    public void getAllRegisteredCoursesFromDB(){
        String url = "http://10.0.2.2/university/all_university_courses.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        SecondCustomAdapter secondCustomAdapter = new SecondCustomAdapter(UniversityCoursesActivity.this, response, username);
                        listView.setAdapter(secondCustomAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Error: " + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

}