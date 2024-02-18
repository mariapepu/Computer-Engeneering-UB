package com.example.coinnote.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import com.example.coinnote.R;

public class MainActivity extends AppCompatActivity {
    Button loginButton, registerButton;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.Login_Button);
        registerButton=findViewById(R.id.Register_Button);
        fAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginIntent = new Intent(v.getContext(), Login.class);
                startActivity(goToLoginIntent);
            }
        });

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), PaginaPrin.class));
            finish();
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterIntent = new Intent(v.getContext(), Register.class);
                startActivity(goToRegisterIntent);
            }
        });

    }






}