package com.aditya.realtimedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("adityaDb");

        databaseReference.setValue("hey,aditya");

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("UserInfo");

        String contact = dbref.push().getKey();

        Users u1 = new Users("Ankit","9876543210");
        dbref.child(contact).setValue(u1);


// Use addValueEventListener to get real-time updates
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // This onDataChange method will be called whenever data at the "UserInfo" location changes.
                // To retrieve all entries, you can iterate through the DataSnapshot.
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users u = dataSnapshot.getValue(Users.class);
                    Log.d("USERS", u.getName() + " " + u.getNo());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DBERROR", error.toString());
            }
        });

    }
}