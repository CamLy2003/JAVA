package com.example.javaiotapp.loginUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaiotapp.contentUI.MainActivity;
import com.example.javaiotapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

     TextView Email, Password;

     Button Button_login, Button_NewAccount;

     private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Button_login = findViewById(R.id.Button_login);
        Button_NewAccount = findViewById(R.id.Register);


        Button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();
            }
        });
        Button_NewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }
    private void log() {
        String EmailNew = Email.getText().toString().trim();
        String PasswordNew = Password.getText().toString().trim();

        //Check empty Email and Password
        if (EmailNew.isEmpty()) {
            Toast.makeText(Login.this, "Email không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (PasswordNew.isEmpty()) {
            Toast.makeText(Login.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(EmailNew, PasswordNew)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Đăng nhập thành công với email " + EmailNew,
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Đăng nhập thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    void register (){
        Intent intent = new Intent(Login.this, register.class);
        startActivity(intent);
    }

}