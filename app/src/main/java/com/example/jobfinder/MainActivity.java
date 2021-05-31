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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView register, loginButton, forgetPassword;
    private EditText loginEmail, loginPassword;
   // private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.loginRegisterText);
        register.setOnClickListener(this);

        loginButton = (Button) findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);

        loginEmail = (EditText) findViewById(R.id.loginEmailAddressInput);
        loginPassword = (EditText) findViewById(R.id.loginPasswordInput);

        progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);

        mAuth = FirebaseAuth.getInstance();

        forgetPassword = (TextView) findViewById(R.id.loginForgotPasswordText);
        forgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginRegisterText:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.loginBtn:
                userLogin();
                break;

                case R.id.loginForgotPasswordText:
                    startActivity(new Intent(this, ForgetPW.class));
                    break;
        }
    }

    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty()) {
            loginEmail.setError("Email is required!");
            loginEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Please enter a valid email!");
            loginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            loginPassword.setError("Password is required!");
            loginPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //redirect to user profile
                    startActivity(new Intent(MainActivity.this, Home.class));

                } else {
                    Toast.makeText(MainActivity.this, "Fail to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}