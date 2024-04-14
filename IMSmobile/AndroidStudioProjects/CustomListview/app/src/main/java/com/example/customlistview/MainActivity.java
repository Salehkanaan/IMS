package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listview);

        String[] texts = new String[]{"HTML", "Cascading Style Sheet", "Javascript", "PHP"};
        int[] imageIds = new int[]{R.drawable.html, R.drawable.css, R.drawable.javascript, R.drawable.php};

        CustomizedAdapter adapter = new CustomizedAdapter(this, texts, imageIds);
        lv.setAdapter(adapter);


    }
}