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
import com.example.javaiotapp.contentUI.MainActivity;
import com.example.javaiotapp.contentUI.user.Gender;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class registerCarInformation extends AppCompatActivity {

    EditText regCarLicensePlate;

    Button  regCarBranch, regCarEndDate;

    Intent intent;

    String email, password, name, dob, gender, address, phoneNum, carLicensePlate, carBranch, carEndDate, carBeginDate;

    private DatePickerDialog datePickerDialog;
    Button Button_register;
    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    private String userUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car_information);

        regCarLicensePlate = findViewById(R.id.regCarLicensePlate);
        regCarBranch = findViewById(R.id.regCarBranch);
        regCarEndDate = findViewById(R.id.regCarEndDate);
        Button_register = findViewById(R.id.carRegisterButton);


        initDatePicker();
        intent = getIntent();

        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        name = intent.getStringExtra("name");
        dob = intent.getStringExtra("dob");
        gender = intent.getStringExtra("gender");
        address = intent.getStringExtra("address");
        phoneNum = intent.getStringExtra("phoneNum");

        regCarBranch.setOnClickListener(v -> chooseBranch());

        regCarEndDate.setOnClickListener(this::openDatePicker);

        Button_register.setOnClickListener(v -> userInforRegister());
    }

    private void userInforRegister() {
        if (isInputValid()) {
            carBeginDate = getTodaysDate().trim();
            carEndDate = regCarEndDate.getText().toString().trim();
            carLicensePlate = regCarLicensePlate.getText().toString().trim();
            carBranch = regCarBranch.getText().toString().trim();

            Map<String, Object> user = new HashMap<>();
            user.put("name", name);
            user.put("dob", dob);
            user.put("gender", gender);
            user.put("address", address);
            user.put("phoneNum", phoneNum);
            user.put("carBeginDate", carBeginDate);
            user.put("carEndDate", carEndDate);
            user.put("carLicensePlate", carLicensePlate);
            user.put("carBranch", carBranch);

            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("Users").document(userUID);

            userRef.set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void documentReference) {
                            // Show a success message
                            Toast.makeText(registerCarInformation.this, "Car information added successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener (new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure and display error message
                            Toast.makeText(registerCarInformation.this, "Error adding car information: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            Intent intent = new Intent(registerCarInformation.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void chooseBranch() {

        String[] carBrands = {
                "Toyota", "Honda", "Ford", "BMW", "Tesla", "Chevrolet", "Nissan", "Hyundai", "Kia",
                "Volkswagen", "Subaru", "Mazda", "Mercedes-Benz", "Audi", "Lexus", "Jaguar", "Volvo",
                "Porsche", "Ferrari", "Lamborghini", "Bugatti", "Aston Martin", "Mitsubishi", "Peugeot",
                "Fiat", "Renault", "Suzuki", "Land Rover", "Mini", "Alfa Romeo", "Cadillac", "Dodge",
                "Jeep", "Chrysler", "Infiniti", "Acura", "Genesis", "Maserati", "Bentley", "Rolls-Royce",
                "McLaren", "Pagani", "Seat", "Skoda", "Citroën", "Ram", "GMC", "Lincoln", "Buick",
                "Saab", "Isuzu", "Daewoo", "Hummer", "Tata", "Mahindra", "Maruti Suzuki", "Proton",
                "Geely", "Changan", "BYD", "Great Wall", "Fisker", "Rivian", "Lucid Motors", "Other"
        };



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Branch")
                .setItems(carBrands, (dialog, which) -> {
                    // Set selected gender
                    String option = carBrands[which];
                    regCarBranch.setText(option.toString());
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
            regCarEndDate.setText(date);
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
        return !regCarLicensePlate.getText().toString().isEmpty()
                && !regCarBranch.getText().toString().isEmpty()
                && !regCarEndDate.getText().toString().isEmpty();
    }



}

