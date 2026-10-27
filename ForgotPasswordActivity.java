package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextInputLayout emailLayout;
    private TextInputEditText etEmail;

    private MaterialButton btnSendOtp;

    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        emailLayout = findViewById(R.id.emailLayout);
        etEmail = findViewById(R.id.etEmail);

        btnSendOtp = findViewById(R.id.btnSendOtp);

        tvBackToLogin = findViewById(R.id.tvBackToLogin);
    }

    private void setupClickListeners() {

        btnBack.setOnClickListener(v -> finish());

        tvBackToLogin.setOnClickListener(v -> finish());

        btnSendOtp.setOnClickListener(v -> sendOtp());
    }

    private void sendOtp() {

        emailLayout.setError(null);

        String email = "";

        if (etEmail.getText() != null) {
            email = etEmail.getText()
                    .toString()
                    .trim();
        }

        if (email.isEmpty()) {

            emailLayout.setError(
                    "Email address is required"
            );

            return;
        }

        if (!Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            emailLayout.setError(
                    "Enter a valid email address"
            );

            return;
        }

        /*
         * API integration will be added later.
         *
         * POST /api/v1/auth/forgot-password
         */

        Intent intent = new Intent(
                ForgotPasswordActivity.this,
                OtpVerificationActivity.class
        );

        intent.putExtra("email", email);
        intent.putExtra("flow", "forgot_password");

        startActivity(intent);
    }
}