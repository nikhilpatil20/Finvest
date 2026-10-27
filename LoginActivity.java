package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;

    private MaterialButton btnLogin;
    private MaterialButton btnGoogle;

    private TextView tvForgotPassword;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {

        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoogle = findViewById(R.id.btnGoogle);

        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);
    }

    private void setupClickListeners() {

        btnLogin.setOnClickListener(v -> validateLogin());

        btnGoogle.setOnClickListener(v -> {

            Toast.makeText(
                    this,
                    "Google Sign-In will be added later",
                    Toast.LENGTH_SHORT
            ).show();

        });

        tvForgotPassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);
        });

        tvRegister.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }

    private void validateLogin() {

        String email = etEmail.getText() != null
                ? etEmail.getText().toString().trim()
                : "";

        String password = etPassword.getText() != null
                ? etPassword.getText().toString()
                : "";

        emailLayout.setError(null);
        passwordLayout.setError(null);

        boolean isValid = true;

        if (email.isEmpty()) {

            emailLayout.setError("Email is required");
            isValid = false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            emailLayout.setError("Enter a valid email address");
            isValid = false;
        }

        if (password.isEmpty()) {

            passwordLayout.setError("Password is required");
            isValid = false;

        } else if (password.length() < 6) {

            passwordLayout.setError(
                    "Password must contain at least 6 characters"
            );

            isValid = false;
        }

        if (isValid) {

            Intent intent = new Intent(
                    LoginActivity.this,
                    HomeActivity.class
            );

            startActivity(intent);

            finish();
        }
    }
}