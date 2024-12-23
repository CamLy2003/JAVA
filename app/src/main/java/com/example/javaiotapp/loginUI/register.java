package com.example.javaiotapp.loginUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaiotapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

public class register extends AppCompatActivity {
    TextView Email_register, Password_register;
    Button Button_register, Button_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Email_register = findViewById(R.id.Email_register);
        Password_register = findViewById(R.id.Password_register);
        Button_register = findViewById(R.id.Button_register);
        Button_login = findViewById(R.id.Button_login);

        Button_register.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                register();
            }
        });
        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, Login.class));
                finish();
            }
        });
    }
    private void register() {
        String emailRegisterNew = Email_register.getText().toString().trim();
        String passwordRegisterNew = Password_register.getText().toString().trim();

        //Check empty Email and Password
        if (emailRegisterNew.isEmpty()) {
            Toast.makeText(register.this, "Email không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwordRegisterNew.isEmpty()) {
            Toast.makeText(register.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth = FirebaseAuth.getInstance();




        mAuth.createUserWithEmailAndPassword(emailRegisterNew, passwordRegisterNew)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful, update the UI or perform further actions
                            Toast.makeText(register.this, "Registration successful with email " + emailRegisterNew,
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Registration", "Registration successful with email " + emailRegisterNew);
                            Intent intent = new Intent(register.this, registerUserInformation.class);

                            // Optionally, redirect the user to another screen after successful registration
                            intent.putExtra("email", emailRegisterNew);
                            intent.putExtra("password", passwordRegisterNew);

                            startActivity(intent);
                            finish();
                        } else {
                            // Handle error if email already exists
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                // This email is already in use
                                Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_SHORT).show();
                            } else {
                                // Some other error
                                Toast.makeText(getApplicationContext(), "Error: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }



}

