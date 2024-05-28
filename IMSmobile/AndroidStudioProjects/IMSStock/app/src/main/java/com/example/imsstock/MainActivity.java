package com.example.imsstock;

//import static android.os.Build.VERSION_CODES.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Button qr;
    EditText edqt;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    ProgressBar pg;

    private static final int PERMISSION_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qr =findViewById(R.id.qr);
        edqt=findViewById(R.id.edqt);
        pg=findViewById(R.id.pg);
        pg.setVisibility(View.INVISIBLE);
        sp = getPreferences(MODE_PRIVATE);
        ed=sp.edit();
        ed.putString("loc","");
        ed.putString("prod","");
        ed.commit();
    }

    public void codeScanner (View v) {
        if (TextUtils.isEmpty(edqt.getText().toString())) {
            edqt.setError("Please enter Quantity!");
        }else{
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                if (R.id.qr == v.getId()) {
                    initQRCodeScanner();
                }
            }
        }
        }


        private void initQRCodeScanner () {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setOrientationLocked(true);
            integrator.setPrompt("Scan a product");
            integrator.setCaptureActivity(CaptureAct.class);
            integrator.initiateScan();
        }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == PERMISSION_REQUEST_CAMERA) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initQRCodeScanner();
                } else {
                    Toast.makeText(this, "Camera permission is required", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            String product = "", location = "";
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            //String format = result.getFormatName();
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Scan cancelled", Toast.LENGTH_LONG).show();
                } else {
                    if (result.getContents().equalsIgnoreCase("r1s1")||
                            result.getContents().equalsIgnoreCase("r2s1") ||
                            result.getContents().equalsIgnoreCase("r1s2") ||
                            result.getContents().equalsIgnoreCase("r2s2")) {
                        location = result.getContents();
                        ed.putString("loc", location);
                        ed.commit();

                    }
                     else{
                        product = result.getContents();
                        String str[]=product.split("-");
                        ed.putString("prod", str[0]);
                        ed.putString("price",str[1]);
                        ed.commit();
                    }
                    String loc = sp.getString("loc", "");
                    String prod = sp.getString("prod", "");

                    if (!TextUtils.isEmpty(loc) && !TextUtils.isEmpty(prod)) {
                        checkItem(prod, loc);
                    } else {
                        Toast.makeText(this, "Scan them all" + loc, Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    public void checkItem (String p, String l){

        String url = "http://192.168.184.115/quantity.php?name=" +p+ "&location=" + l ;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        int res=Integer.parseInt(s);
                         int q=Integer.parseInt(edqt.getText().toString());
                       if(res>0){
                            updtItem(p,l,q);
                        }
                        else {
                            addItem(p,l,q);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error: ";
                        errorMessage += error.toString();
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    }
                });
        queue.add(request);
    }
    public void addItem (String p, String l,int q){
        String price=sp.getString("price","");
        pg.setVisibility(View.VISIBLE);
        String url = "http://192.168.184.115/additem.php?name=" +p+"&price="+price+ "&location=" + l + "&quantity=" + q;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        pg.setVisibility(View.INVISIBLE);
                        if(!s.equals("-1")){
                            Toast.makeText(MainActivity.this, " item is added!", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(MainActivity.this, "No item is added!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error: ";
                        errorMessage += error.toString();
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
        ed.clear();
        ed.commit();
        queue.add(request);
    }
    public void updtItem (String p, String l,int q){
        pg.setVisibility(View.VISIBLE);
        String url =  "http://192.168.184.115/updtitem.php?name=" +p + "&location=" + l + "&quantity=" + q;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        pg.setVisibility(View.INVISIBLE);
                        if(!s.equals("-1")){
                            Toast.makeText(MainActivity.this, " item is updated!", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(MainActivity.this, "No item is added!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error: ";
                        errorMessage += error.toString();
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
        ed.clear();
        ed.commit();
        queue.add(request);
    }

}
