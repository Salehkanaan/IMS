package com.example.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText nameEditText;
CheckBox carcheckbox,motorcheckbox;
RadioButton maleRadioButton,femaleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText=findViewById(R.id.nameEd);
        carcheckbox=findViewById(R.id.car);
        motorcheckbox=findViewById(R.id.motor);
        maleRadioButton=findViewById(R.id.male);
        femaleRadioButton=findViewById(R.id.female);

    }
    public void cbFunction(View v){
        boolean isChecked= ((CheckBox)v).isChecked();
        if(v.getId()==R.id.car){
          if(isChecked){
              Toast.makeText(this,"your have a car",Toast.LENGTH_SHORT).show();

          }
          else
              Toast.makeText(this,"your dont have a car",Toast.LENGTH_SHORT).show();
        } else if (v.getId()==R.id.motor) {
            if(isChecked){
                Toast.makeText(this,"your have a motor",Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(this,"your dont have a motor",Toast.LENGTH_SHORT).show();
        }

    }
    public void rbFunction(View v){
        if(v.getId()==R.id.male){
            Toast.makeText(this,"Your Are Male",Toast.LENGTH_SHORT).show();

            }
        if(v.getId()==R.id.female){
            Toast.makeText(this,"Your Are Female",Toast.LENGTH_SHORT).show();
        }
        }
    public void submitFunction(View v){
        String name=nameEditText.getText().toString();
        if(name.equals("")){
            nameEditText.setError("it cant be empty");
            return;
        }
        boolean haveCar,haveMotor,isMale;

        haveCar=carcheckbox.isChecked();
        haveMotor=motorcheckbox.isChecked();
        isMale=maleRadioButton.isChecked();
        String msg="Your name is: "+name;
        if(haveCar){
            msg+=", your have a car";
        }
        if(haveMotor){
            msg+=",you have a motor";
        }
        if(isMale){
            msg+=",you are a male";
        }
        else  msg+=",you are a female";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}