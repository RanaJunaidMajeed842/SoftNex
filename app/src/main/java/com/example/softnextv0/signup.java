package com.example.softnextv0;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class signup extends AppCompatActivity {
    FirebaseAuth firebase;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        firebase = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        EditText nameEditText = findViewById(R.id.name);
        EditText addressEditText = findViewById(R.id.address);
        EditText emailEditText = findViewById(R.id.email_input);
        EditText passwordEditText = findViewById(R.id.password_input);
        EditText confirmPasswordEditText = findViewById(R.id.confirm_password);
        EditText numberEditText = findViewById(R.id.number);
        Button signupButton = findViewById(R.id.login_button);
        TextView registerPrompt = findViewById(R.id.register_prompt);


        signupButton.setOnClickListener(v -> validateAndSignup(nameEditText, addressEditText, emailEditText, passwordEditText, confirmPasswordEditText , numberEditText));
        registerPrompt.setOnClickListener(v -> startActivity(new Intent(signup.this, login.class))
        );
    }

    private void validateAndSignup(EditText nameEditText, EditText addressEditText, EditText emailEditText, EditText passwordEditText, EditText confirmPasswordEditText , EditText numberEditText) {
        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String number = numberEditText.getText().toString().trim();

        progressDialog.show();

        if (name.isEmpty()) {
            showToast("Name is required");
            return;
        }

        if (address.isEmpty()) {
            showToast("Role is required");
            return;
        }

        if (email.isEmpty()) {
            showToast("Email is required");
            return;
        }
        if (number.isEmpty()) {
            showToast("Number is required");
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address");
            return;
        }

        if (password.isEmpty()) {
            showToast("Password is required");
            return;
        }

        if (password.length() < 6) {
            showToast("Password must be at least 6 characters long");
            return;
        }

        if (confirmPassword.isEmpty()) {
            showToast("Please confirm your password");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showToast("Passwords do not match");
            return;
        }
        firebase.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        new Intent(signup.this, login.class);
                        firebaseFirestore.collection("Users")
                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                .set(new UserModel(name , number , address, email ));
                        finish();
                        progressDialog.cancel();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signup.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                    }
                });
    }

    private void showToast(String message) {
        Toast.makeText(signup.this, message, Toast.LENGTH_SHORT).show();
    }

}
