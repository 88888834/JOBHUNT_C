package com.example.jobhunt_C;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    RadioGroup userTypeRadioGroup = findViewById(R.id.userTypeRadioGroup); // Initialize RadioGroup
    Button loginButton = findViewById(R.id.loginButton);
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Spinner citySpinner;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        usernameEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.password);
        citySpinner = findViewById(R.id.spinner_cities);
        Button loginButton = findViewById(R.id.loginButton);
        TextView signupText = findViewById(R.id.signupText);
        databaseHelper = new DatabaseHelper(this);
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String selectedCity = citySpinner.getSelectedItem().toString();
            String userType;
            int selectedRadioButtonId = userTypeRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId == R.id.recruteurRadioButton) {
                userType = "RECRUTEUR";
            } else if (selectedRadioButtonId == R.id.candidatRadioButton) {
                userType = "CANDIDAT";
            } else {
                Toast.makeText(RegisterActivity.this, "Please select a user type", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean success = addUserToDatabase(username, password, selectedCity, userType);
            if (success) {
                Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish(); // Optionally finish this activity to prevent user from going back to it
            } else {
                Toast.makeText(RegisterActivity.this, "Failed to register user. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
        signupText.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });
    }

    private boolean addUserToDatabase(String username, String password, String city, String userTypeRadioGroup) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_CITY, city);
           values.put(DatabaseHelper.COLUMN_GENRE, userTypeRadioGroup);
           long result = db.insert(DatabaseHelper.TABLE_NAME, null, values);
           db.close();
           return result != -1;
    }
}
