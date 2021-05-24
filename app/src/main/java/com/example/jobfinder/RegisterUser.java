package com.example.jobfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.StrongBoxUnavailableException;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView returnToLogin, registerUser;
    private EditText registerFullName, registerEmail, registerPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        
        returnToLogin = (TextView) findViewById(R.id.registerReturnToLoginText);
        returnToLogin.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(this);

        registerFullName = (EditText) findViewById(R.id.registerNameInput);
        registerEmail = (EditText) findViewById(R.id.registerEmailAdressInput);
        registerPassword = (EditText) findViewById(R.id.registerPasswordInput);

        progressBar = (ProgressBar) findViewById(R.id.registerProgressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerReturnToLoginText:
                startActivity( new Intent( this, MainActivity.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fullName = registerFullName.getText().toString().trim();
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();

        if (fullName.isEmpty()) {
            registerFullName.setError("Full name is required!");
            registerFullName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            registerEmail.setError("Email is required");
            registerEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Please provide a valid email!");
            registerEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerPassword.setError("Password should be at least 6 characters!");
            registerPassword.requestFocus();
            return;
        }



    }
}