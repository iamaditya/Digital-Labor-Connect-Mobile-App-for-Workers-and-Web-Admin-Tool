package com.aditya.labourdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    Spinner spiner;
    FirebaseAuth auth;
    Button b1,sub;
    TextView tv;
    FirebaseUser user;
    ProgressBar pb;

    String[] loca = {"Himachal","Punjab","Haryana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        // basic inializations
        auth = FirebaseAuth.getInstance();
        b1 = findViewById(R.id.button5);
        tv = findViewById(R.id.textView);
        user = auth.getCurrentUser();
        spiner = findViewById(R.id.spinner);
        sub = findViewById(R.id.button6);
        pb = findViewById(R.id.progressBar2);

        // setting drop down menu for locations
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, loca);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(UserActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // logout functionality
        if(user == null){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }else{
            tv.setText(user.getEmail());
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        // Submit Button

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);


            }
        });


    }
}