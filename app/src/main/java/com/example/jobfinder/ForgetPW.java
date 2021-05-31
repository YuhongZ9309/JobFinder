package com.example.jobfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPW extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;
    private TextView resetLoginPage, resetRegisterPage;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_p_w);

        emailEditText =(EditText) findViewById(R.id.resetEmailInput);
        resetPasswordButton =(Button) findViewById(R.id.resetPasswordButton);

        resetLoginPage = findViewById(R.id.resetLoginText);
        resetRegisterPage = findViewById(R.id.resetRegisterText);

        auth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.resetProgressBar);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        resetLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPW.this, MainActivity.class));
            }
        });
        resetRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPW.this, RegisterUser.class));
            }
        });


    }
    private void resetPassword(){

        String resetPasswordEmail = emailEditText.getText().toString().trim();

        if(resetPasswordEmail.isEmpty()){
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(resetPasswordEmail).matches()){
            emailEditText.setError("Please provide valid email");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(resetPasswordEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(ForgetPW.this, "Email sent! Please check your email!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPW.this, MainActivity.class));
                } else {
                    Toast.makeText(ForgetPW.this, "Try again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}