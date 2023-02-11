package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    EditText Enternum;
    Button miles;
    Button centimeters;
    Button meters;
    Button kilometers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.textView3);
        Enternum= findViewById(R.id.editTextNumberDecimal);
        miles = findViewById(R.id.miles);
        meters = findViewById(R.id.meters);
        centimeters = findViewById(R.id.centimeters);
       kilometers = findViewById(R.id.kilometer);

       meters.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String num = Enternum.getText().toString();
               int number = Integer.parseInt(num);
               float meters = (number*1000);
               output.setText("Value:"+meters);
           }
       });

        kilometers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = Enternum.getText().toString();
                int number = Integer.parseInt(num);
                double kilometer = (number*0.001);
                output.setText("Value:"+kilometer);
            }
        });

        centimeters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = Enternum.getText().toString();
                int number = Integer.parseInt(num);
                float  centimeters= (number*100);
                output.setText("Value:"+centimeters);
            }
        });

        miles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = Enternum.getText().toString();
                int number = Integer.parseInt(num);
                double miles = (number/1.609);
                output.setText("Value:"+miles);
            }
        });
    }
}