package com.example.javaiotapp.loginUI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaiotapp.R;
import com.example.javaiotapp.contentUI.user.Gender;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class registerUserInformation extends AppCompatActivity {

    EditText regName,  regAddress, regPhoneNum;

    Button  regGender, regDOB, nextButton;

    Intent intent;

    String email, password;

    private DatePickerDialog datePickerDialog;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_information);

        regName = findViewById(R.id.regName);
        regGender = findViewById(R.id.regGender);
        regDOB = findViewById(R.id.regDate);
        regAddress = findViewById(R.id.regAddress);
        regPhoneNum = findViewById(R.id.regPhoneNumber);
        nextButton = findViewById(R.id.btnNext);

        initDatePicker();

        intent = getIntent();

        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        regGender.setOnClickListener(v -> chooseGender());

        nextButton.setOnClickListener(v -> changeToCarInformation());

        regDOB.setOnClickListener(v -> openDatePicker(v));

    }

    private void changeToCarInformation() {
        if (isInputValid()) {
            Intent intent = new Intent(registerUserInformation.this, registerCarInformation.class);

            // Optionally, redirect the user to another screen after successful registration
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            intent.putExtra("name", regName.getText().toString());
            intent.putExtra("gender", regGender.getText().toString());
            intent.putExtra("dob", regDOB.getText().toString());
            intent.putExtra("address", regAddress.getText().toString());
            intent.putExtra("phoneNum", regPhoneNum.getText().toString());

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void chooseGender() {

        Gender[]genderOption = {Gender.Male, Gender.Female, Gender.Other};

        String[] genderNames = new String[genderOption.length];
        for (int i = 0; i < genderOption.length; i++) {
            genderNames[i] = genderOption[i].toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Gender")
                .setItems(genderNames, (dialog, which) -> {
                    // Set selected gender
                    Gender option = genderOption[which];
                    regGender.setText(option.toString());
                })
                .show();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            regDOB.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        String dob = getTodaysDate();

         // Parse the DOB to set DatePicker default values
        String[] parts = dob.split(" ");
        int year = Integer.parseInt(parts[2]);
        int month = getMonthNumber(parts[0]) - 1; // Convert to zero-based
        int day = Integer.parseInt(parts[1]);
        cal.set(year, month, day);



        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }


    private int getMonthNumber(String month) {
        switch (month) {
            case "JAN": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "APR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AUG": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DEC": return 12;
            default: return 1; // Default to JAN
        }
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isInputValid() {
        // Example validation logic
        return !regName.getText().toString().isEmpty()
                && !regGender.getText().toString().isEmpty()
                && !regDOB.getText().toString().isEmpty()
                && !regAddress.getText().toString().isEmpty()
                && !regPhoneNum.getText().toString().isEmpty();
    }



}

