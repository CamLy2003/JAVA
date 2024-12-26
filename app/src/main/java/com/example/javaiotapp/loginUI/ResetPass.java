package com.example.javaiotapp.loginUI;
import static android.content.ContentValues.TAG;

import com.example.javaiotapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {

    TextView Email;
    Button Button_reset, Button_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        Email = findViewById(R.id.Email);
        Button_reset = findViewById(R.id.Button_reset);
        Button_login = findViewById(R.id.Button_login);

        mAuth = FirebaseAuth.getInstance();

        Button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResetPassword();
            }
        });

        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });
    }

    private void sendResetPassword() {
        String email = Email.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(ResetPass.this, "Email không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            navigateToLogin();
                        }

                    }
                });



    }

    private void navigateToLogin() {
        Intent intent = new Intent(ResetPass.this, Login.class);
        startActivity(intent);
    }
}