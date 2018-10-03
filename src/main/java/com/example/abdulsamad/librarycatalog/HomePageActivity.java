package com.example.abdulsamad.librarycatalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        TextView textView = (TextView) findViewById(R.id.homepage_login_label);
        FirebaseUser user = getIntent().getParcelableExtra("user");
        
    }


}
