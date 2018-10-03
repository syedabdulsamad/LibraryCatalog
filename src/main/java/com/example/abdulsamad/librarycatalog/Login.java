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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = authResult.getUser();
                    if (user != null && user.isEmailVerified()) {
                        currentUser = user;
                        Toast.makeText(getApplicationContext(), "User logged in successfully", LENGTH_SHORT).show();
                        loadHomePage();
                    } else if (user.isEmailVerified() == false) {
                        Toast.makeText(getApplicationContext(), "Email not verified", LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Login failed.\n " + e.getMessage(), LENGTH_SHORT).show();
                }
            });
        }


    }

    private void loadHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("user", currentUser);
        startActivity(intent);


    }

    private void registerUser() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void forgotPassword() {
        // Implement forgot password logic

    }
}
