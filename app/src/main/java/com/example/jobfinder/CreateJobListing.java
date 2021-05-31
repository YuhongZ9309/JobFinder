package com.example.jobfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateJobListing extends AppCompatActivity {

    ImageView backIcon;
    EditText jobTitleInput, jobPriceInput, jobDescriptionInput;
    Button jobPostButton;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job_listing);

        backIcon = findViewById(R.id.toolbarBackButton);
        jobTitleInput = findViewById(R.id.createJobTitleEditText);
        jobPriceInput = findViewById(R.id.createJobPriceEditText);
        jobDescriptionInput = findViewById(R.id.createJobDescriptionEditText);
        jobPostButton = findViewById(R.id.createJobPostButton);

        progressBar = findViewById(R.id.createJobProgressBar);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(CreateJobListing.this, "You Click on the back button", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateJobListing.this, Home.class));
            }
        });

        jobPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = jobTitleInput.getText().toString().trim();
                String jobPrice = jobPriceInput.getText().toString().trim();
                String jobDescription = jobDescriptionInput.getText().toString().trim();

                if (jobTitle.isEmpty()) {
                    jobTitleInput.setError("Title cannot be empty!");
                    jobTitleInput.requestFocus();
                    return;
                }
                if (jobPrice.isEmpty()) {
                    jobPriceInput.setError("Price cannot be empty!");
                    jobPriceInput.requestFocus();
                    return;
                }
                if (jobDescription.isEmpty()) {
                    jobDescriptionInput.setError("Description cannot be empty!");
                    jobDescriptionInput.requestFocus();
                    return;
                }

               progressBar.setVisibility(View.VISIBLE);
                System.out.println("This is a text outputted by ME");

                user = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users");
                userID = user.getUid();

                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if (userProfile != null) {
                            String fullName = userProfile.fullName;
                            String email = userProfile.email;

                            System.out.println(fullName + ", " + email);
                            Job job = new Job (fullName, email, jobTitle, jobPrice, jobDescription);

                            System.out.println(job);

                            reference = FirebaseDatabase.getInstance().getReference("Jobs");
                            reference.push().setValue(job);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CreateJobListing.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
                    }
                });


                progressBar.setVisibility(View.GONE);
                Toast.makeText(CreateJobListing.this, "Job successfully posted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CreateJobListing.this, Home.class));
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

    }
}