package com.example.jobfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPW extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_p_w);

        emailEditText =(EditText) findViewById(R.id.loginEmailAddressInput);
        resetPasswordButton =(Button) findViewById(R.id.resetPassword);

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.onClickListener());
            @Override
            public void onClick(View v){
                resetPassword();
        }

        private void resetPassword(){

            String LoginEmailAddressInput = emailEditText.getText().toString().trim();

            if(LoginEmailAddressInput.isEmpty()){
                emailEditText.setError("Email is required");
                emailEditText.requestFocus();
            }

            if(!Pattern.EMAIl_ADDRESS.matcher(LoginEmailAddressInput).matches()){
                emailEditText.setError("Please provide valid email");
                emailEditText.requestFocus();
                return;
            }

        }






    }
}