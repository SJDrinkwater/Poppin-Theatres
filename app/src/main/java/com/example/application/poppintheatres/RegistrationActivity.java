package com.example.application.poppintheatres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextEmail, editTextPhone, editTextAddress, editTextBirthday;
    Button buttonRegister;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextBirthday = findViewById(R.id.editTextBirthday);
        buttonRegister = findViewById(R.id.buttonRegister);

        editTextBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((s.length() == 2 || s.length() == 5) && !s.toString().endsWith("/")) {
                    editTextBirthday.setText(s + "/");
                    editTextBirthday.setSelection(s.length() + 1);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 2 || s.length() == 5) {
                    String[] split = s.toString().split("/");
                    if (split.length >= 1) {
                        int day = Integer.parseInt(split[0]);
                        if (day < 1 || day > 31) {
                            Toast.makeText(RegistrationActivity.this, "Day cannot be less than 1 or more than 31", Toast.LENGTH_SHORT).show();
                            s.clear();
                            return;
                        }
                    }
                    if (split.length >= 2) {
                        int month = Integer.parseInt(split[1]);
                        if (month < 1 || month > 12) {
                            Toast.makeText(RegistrationActivity.this, "Month cannot be less than 1 or more than 12", Toast.LENGTH_SHORT).show();
                            s.clear();
                            return;
                        }
                    }
                }
                if (s.length() >= 10) { // length of dd/MM/yyyy
                    String[] split = s.toString().split("/");
                    if (split.length == 3) {
                        int year = Integer.parseInt(split[2]);
                        if (year > 2023) {
                            Toast.makeText(RegistrationActivity.this, "Year cannot be more than 2023", Toast.LENGTH_SHORT).show();
                            s.clear();
                        }
                    }
                }
            }
        });


        //user clicks register, if all conditions apply, a new user would be added into the database

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String birthday = editTextBirthday.getText().toString().trim();

                if (!isValidPassword(password)) {
                    Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, 1 uppercase letter, 1 number, and 1 special character", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidPhoneNumber(phone)) {
                    Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidBirthday(birthday)) {
                    Toast.makeText(getApplicationContext(), "Invalid birthday format (DD/MM/YYYY)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!databaseHelper.checkUser(username)) {
                    databaseHelper.addUser(username, password, email, phone, address, birthday);
                    Toast.makeText(RegistrationActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+?\\d{10,15}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidBirthday(String birthday) {
        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher matcher = pattern.matcher(birthday);
        return matcher.matches();
    }

}




