package com.example.plantingyourway;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTxtName, editTxtEmail, editTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Planting Your Way");

        editTxtName = findViewById(R.id.editTxtName);
        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPassword = findViewById(R.id.editTxtPassword);
    }

    public void signUp(View view){

        String userName = editTxtName.getText().toString();
        String userEmail = editTxtEmail.getText().toString();
        String userPassword = editTxtPassword.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Please enter the name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Please enter the email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Please enter the Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 8){
            Toast.makeText(this, "Password too short... Enter a password of 8 or more characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }
    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

}