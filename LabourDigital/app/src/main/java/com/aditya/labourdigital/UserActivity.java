package com.aditya.labourdigital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;

    Spinner spiner;
    FirebaseAuth auth;
    Button b1,sub;
    TextView tv;
    FirebaseUser user;
    ProgressBar pb;
    DatabaseReference db;
    String value;


    String[] loca = {"Himachal","Punjab","Haryana"};

    int year, month, day;

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
        db = FirebaseDatabase.getInstance().getReference().child("Users");

        //user Details
        ed1 = findViewById(R.id.editTextText);
        ed2 = findViewById(R.id.editTextText2);

        //disabling keyboard for calender
        ed2.setInputType(InputType.TYPE_NULL); // or InputType.TYPE_DATETIME_VARIATION_DATE
        ed2.setFocusable(true);
        ed2.setClickable(true);
        ed2.setFocusableInTouchMode(true);

        ed3 = findViewById(R.id.editTextPhone);
        ed4 = findViewById(R.id.editTextTextPostalAddress);


        // setting on click listener for calander

        Calendar calender = Calendar.getInstance();
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calender.get(calender.YEAR);
                month = calender.get(calender.MONTH);
                day = calender.get(calender.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        ed2.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        // setting drop down menu for locations
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, loca);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 value = adapterView.getItemAtPosition(i).toString();
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
                // get user detail as string

                String nameofuser = ed1.getText().toString();
                String DOB = ed2.getText().toString();
                String phonenumber = ed3.getText().toString();
                String address = ed4.getText().toString();
                String currMail = tv.getText().toString();
                Log.d("Values",nameofuser+" "+DOB+" "+phonenumber+" "+address+" "+value);



                HashMap<String, String> hashmap = new HashMap();

                hashmap.put("Name",nameofuser);
                hashmap.put("DOB",DOB);
                hashmap.put("Phone",phonenumber);
                hashmap.put("Address",address);
                hashmap.put("Work",value);

                db.child(currMail.replace('.', ',')).setValue(hashmap).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {
                        pb.setVisibility(View.GONE);
                        Toast.makeText(UserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pb.setVisibility(View.GONE);
                        Toast.makeText(UserActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}