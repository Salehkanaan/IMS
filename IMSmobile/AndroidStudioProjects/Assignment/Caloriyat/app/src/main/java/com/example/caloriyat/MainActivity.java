package com.example.caloriyat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
EditText weight,height;
Spinner spinner;
String selectedAge;
boolean male;
RadioButton m,f;
double heightm,weightp,weightkg,bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        m=findViewById(R.id.male);
        f=findViewById(R.id.female);


    ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAge = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public double toPound(Double w1){
        weightp=w1*2.205;
        return weightp;
    }
    public double toInches(Double h1){
      double  heightp=h1*39.37;
        return heightp;
    }
  public void  bmiFunction(View v){
        String w=weight.getText().toString();
        String h=height.getText().toString();
        if(TextUtils.isEmpty(w)){
            weight.setError("Empty text");
            return;
        } else if (h.equals("")) {
          height.setError("Empty text");
          return;
        }
        heightm= Double.parseDouble(height.getText().toString())/(100);
        weightkg=Double.parseDouble(weight.getText().toString());
        bmi=  weightkg/(heightm*heightm);
        if(bmi<16){
     Toast.makeText(this,"Your are severely underweight ",Toast.LENGTH_SHORT).show();
 }
        else if (bmi>=16&&bmi<18.5) {
     Toast.makeText(this,"Your are underweight",Toast.LENGTH_SHORT).show();
 }
        else if (bmi>=18.5&&bmi<25) {
     Toast.makeText(this,"Your are normal",Toast.LENGTH_SHORT).show();
 }
        else if (bmi>30.0) {
     Toast.makeText(this,"Your have obesity",Toast.LENGTH_SHORT).show();
 }
  }
  public boolean isMale(View v){
        if(v.getId()==R.id.male){
            male=true;
        }
        if(v.getId()==R.id.female){
            male=false;
        }
        return male;

  }
  public void calFunction(View v){
Intent i=new Intent(this,MainActivity2.class);
Double cal,cal1,cal2;
       String s= spinner.getSelectedItem().toString();
     if(isMale(v)){
         cal=665+(6.3*toPound(weightkg))+(12.9*toInches(heightm))-(6.8*24);
         Double calories= (double)((float)Math.round(cal * 100) / 100);
        i.putExtra("d",calories);
        startActivity(i);
     } else {
     if(s.equals("Below 24")){
        cal1=655+(4.3*toPound(weightkg))+(4.7*toInches(heightm))-(4.7*24);
        Double calories= (double) ((float)Math.round(cal1 * 100) / 100);
        i.putExtra("d",calories);
        startActivity(i);
}
     else if (s.equals("Above 24")){
        cal2=455+(4.3*toPound(weightkg))+(4.7*toInches(heightm))-(4.7*24);
      Double  calories= (double) ((float)Math.round(cal2 * 100) / 100);
        i.putExtra("d",calories);
        startActivity(i);
}
     }
  }


}