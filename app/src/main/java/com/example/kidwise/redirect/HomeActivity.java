package com.example.kidwise.redirect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwise.R;
import com.example.kidwise.account.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private Button learning;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        firstNameTextView = findViewById(R.id.firstNameTextView);
        learning = findViewById(R.id.learning);


        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get current user ID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            // Retrieve user information from Firebase Database
            databaseReference.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Retrieve user information
                        String firstName = dataSnapshot.child("firstName").getValue(String.class);

                        // Set user information to TextView
                        firstNameTextView.setText(firstName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }

        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent learningActivity = new Intent(getApplicationContext(), LearningActivity.class);
                startActivity(learningActivity);
                finish();
            }
        });
    }
}