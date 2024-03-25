package com.example.jobhunt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private EditText titleEditText;         // EditText for the title
    private Spinner jobsSpinner;            // Spinner for job selection
    private Spinner sectorSpinner;          // Spinner for sector selection
    private EditText contractTypeEditText;  // EditText for contract type
    private EditText descriptionEditText;   // EditText for description
    private Spinner contractSpinner;        // Spinner for contract selection
    private Button nextButton;              // Button for the next action

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        // Initialize the views
        titleEditText = findViewById(R.id.title);
        jobsSpinner = findViewById(R.id.jobs);
        sectorSpinner = findViewById(R.id.spinnerSecteur);
        contractTypeEditText = findViewById(R.id.TypeContrat);
        descriptionEditText = findViewById(R.id.description);
        contractSpinner = findViewById(R.id.spinnercontract);
        nextButton = findViewById(R.id.loginButton);

        // Set a click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFormData();
            }
        });
    }

    private void saveFormData() {
        // Retrieve the form data from the views
        String title = titleEditText.getText().toString().trim();
        String job = jobsSpinner.getSelectedItem().toString();
        String sector = sectorSpinner.getSelectedItem().toString();
        String contractType = contractTypeEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String contract = contractSpinner.getSelectedItem().toString();

        // Validate the form data (e.g., check if required fields are filled)

        // Insert the form data into the 'userinformation' table
        // Replace this code with your actual database insertion logic
        // For demonstration purposes, we simply display a toast message
        String message = "Form data: \n" +
                "Title: " + title + "\n" +
                "Job: " + job + "\n" +
                "Sector: " + sector + "\n" +
                "Contract Type: " + contractType + "\n" +
                "Description: " + description + "\n" +
                "Contract: " + contract;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}