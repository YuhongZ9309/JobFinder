package com.example.jobfinder;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName,email);

                            FirebaseDatabase.getInstance().getReference( "Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "User have been registered successfully! Please login!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterUser.this, MainActivity.class));

                                    } else {
                                        Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }
}