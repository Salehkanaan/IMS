package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
EditText phoneed;
ImageView image;
int call_request_permission_code=100;
int camera_request_permission_code=122342;
int opencamera_request_code=42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneed=findViewById(R.id.phone_ed);
        image=findViewById(R.id.photo);

    }

    public void dialFunction(View v){
        String phone=phoneed.getText().toString();
        Uri uri=Uri.parse("tel:"+phone);
        Intent i=new Intent(Intent.ACTION_DIAL,uri);
        startActivity(i);
    }

    public void callFunction(View v){
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
        doCall();
    }
    else{
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},call_request_permission_code);
    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==call_request_permission_code){
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                doCall();
            }
            else{
                Toast.makeText(this,"You press denied",Toast.LENGTH_SHORT).show();
            }
        }

        else if (requestCode==camera_request_permission_code) {
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                takePhoto();
            }
            else{
                Toast.makeText(this,"You press denied",Toast.LENGTH_SHORT).show();
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void doCall(){
        String phone=phoneed.getText().toString();
        Uri uri=Uri.parse("tel:"+phone);
        Intent i=new Intent(Intent.ACTION_CALL,uri);
        startActivity(i);
    }


    public void imageFunction(View v){
        //if in onclicklistener we use
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            takePhoto();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.CAMERA},camera_request_permission_code);
        }
    }

    public void takePhoto(){
Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
startActivityForResult(i,opencamera_request_code);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode==opencamera_request_code){
            if(resultCode==RESULT_OK){
               Bundle b=data.getExtras();
                Bitmap img=(Bitmap) b.get("data");//data.getExtras().get("data")
              image.setImageBitmap(img);

            } else if (resultCode==RESULT_CANCELED) {
                Toast.makeText(this,"Canceled!",Toast.LENGTH_SHORT).show();
           }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}