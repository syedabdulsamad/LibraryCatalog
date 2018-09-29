package com.example.abdulsamad.librarycatalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private Button loginButton = null;
    private TextView forgotPasswordLabel = null;
    private TextView registerUserLabel = null;
    private EditText emailTextField = null;
    private EditText passwordTextField = null;
    private FirebaseUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        forgotPasswordLabel = (TextView) findViewById(R.id.forgotPasswordLabel);
        registerUserLabel = (TextView) findViewById(R.id.registerUserLabel);
        emailTextField = (EditText) findViewById(R.id.login_emailTextView);
        passwordTextField = (EditText) findViewById(R.id.login_passwordTextView);
        auth = FirebaseAuth.getInstance();
        setOnClickListeners();


    }

    private void setOnClickListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
        forgotPasswordLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });
        registerUserLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void authenticateUser() {
        String email = emailTextField.getText().toString();
        String password = passwordTextField.getText().toString();
        if (email.length() != 0 && password.length() != 0) {
            auth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = task.getResult().getUser();
                            if (task.isSuccessful() && user != null) {
                                currentUser = user;
                                Toast.makeText(getApplicationContext(), "User logged in successfully", LENGTH_SHORT).show();
                            }
                        }
                    });

        }


    }

    private void registerUser() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void forgotPassword() {

    }


}
