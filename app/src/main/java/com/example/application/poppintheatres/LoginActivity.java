package com.example.application.poppintheatres;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                int userId = databaseHelper.verifyUser(username, password); // Use getUserId method

                if (userId != -1) {
                    // Successful login
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    // Navigate to the main activity or another activity
                    Intent intent = new Intent(LoginActivity.this, PerformanceListActivity.class);
                    intent.putExtra("user_id", userId);
                    startActivity(intent);
                } else {
                    // Unsuccessful login
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textViewRegister = findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
    public void openFacebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }

    public void openTwitter(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
        startActivity(intent);
    }

    public void openLinkedIn(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com"));
        startActivity(intent);
    }
    public void openInstagram(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
        startActivity(intent);
    }
}




