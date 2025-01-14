package com.example.softnextv0;

import android.content.Intent;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailInput = findViewById(R.id.email_input);
        EditText passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);
        TextView registerPrompt = findViewById(R.id.register_prompt);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> validateAndLogin(emailInput, passwordInput));

        registerPrompt.setOnClickListener(v ->
                startActivity(new Intent(login.this, signup.class))
        );
    }

    private void validateAndLogin(EditText emailInput, EditText passwordInput) {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            showToast("Email is required");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showToast("Password is required");
            return;
        }

        // Navigate to home activity
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(login.this, home.class));
                        Toast.makeText(login.this , "Login Successful" , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });

        finish();
    }

    private void showToast(String message) {
        Toast.makeText(login.this, message, Toast.LENGTH_SHORT).show();
    }
}
