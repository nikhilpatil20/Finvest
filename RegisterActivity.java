package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private ImageButton btnBack;

    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout mobileLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;

    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etMobile;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;

    private CheckBox cbTerms;

    private MaterialButton btnCreateAccount;

    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);

        nameLayout = findViewById(R.id.nameLayout);
        emailLayout = findViewById(R.id.registerEmailLayout);
        mobileLayout = findViewById(R.id.mobileLayout);
        passwordLayout = findViewById(R.id.registerPasswordLayout);
        confirmPasswordLayout =
                findViewById(R.id.confirmPasswordLayout);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword =
                findViewById(R.id.etConfirmPassword);

        cbTerms = findViewById(R.id.cbTerms);

        btnCreateAccount =
                findViewById(R.id.btnCreateAccount);

        tvLogin = findViewById(R.id.tvLogin);
    }

    private void setupClickListeners() {

        btnBack.setOnClickListener(v -> finish());

        tvLogin.setOnClickListener(v -> finish());

        btnCreateAccount.setOnClickListener(
                v -> validateRegistration()
        );
    }

    private void validateRegistration() {

        clearErrors();

        String name = getText(etName);
        String email = getText(etEmail);
        String mobile = getText(etMobile);
        String password = getText(etPassword);
        String confirmPassword = getText(etConfirmPassword);

        boolean isValid = true;

        // Full Name

        if (name.isEmpty()) {

            nameLayout.setError(
                    "Full name is required"
            );

            isValid = false;

        } else if (name.length() < 3) {

            nameLayout.setError(
                    "Enter a valid full name"
            );

            isValid = false;
        }

        // Email

        if (email.isEmpty()) {

            emailLayout.setError(
                    "Email is required"
            );

            isValid = false;

        } else if (!Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            emailLayout.setError(
                    "Enter a valid email address"
            );

            isValid = false;
        }

        // Mobile

        if (mobile.isEmpty()) {

            mobileLayout.setError(
                    "Mobile number is required"
            );

            isValid = false;

        } else if (mobile.length() != 10) {

            mobileLayout.setError(
                    "Enter a valid 10-digit mobile number"
            );

            isValid = false;
        }

        // Password

        if (password.isEmpty()) {

            passwordLayout.setError(
                    "Password is required"
            );

            isValid = false;

        } else if (password.length() < 8) {

            passwordLayout.setError(
                    "Password must contain at least 8 characters"
            );

            isValid = false;
        }

        // Confirm Password

        if (confirmPassword.isEmpty()) {

            confirmPasswordLayout.setError(
                    "Please confirm your password"
            );

            isValid = false;

        } else if (!password.equals(confirmPassword)) {

            confirmPasswordLayout.setError(
                    "Passwords do not match"
            );

            isValid = false;
        }

        // Terms

        if (!cbTerms.isChecked()) {

            Toast.makeText(
                    RegisterActivity.this,
                    "Please accept Terms & Conditions",
                    Toast.LENGTH_SHORT
            ).show();

            isValid = false;
        }

        // Valid

        if (isValid) {

            registerUser(
                    name,
                    email,
                    mobile,
                    password
            );
        }
    }

    private void clearErrors() {

        nameLayout.setError(null);
        emailLayout.setError(null);
        mobileLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);
    }

    private String getText(
            TextInputEditText editText
    ) {

        if (editText.getText() == null) {
            return "";
        }

        return editText
                .getText()
                .toString()
                .trim();
    }

    private void registerUser(
            String name,
            String email,
            String mobile,
            String password
    ) {

        /*
         * TEMPORARY DEVELOPMENT FLOW
         *
         * Later:
         * POST /api/v1/auth/register
         */

        Intent intent = new Intent(
                RegisterActivity.this,
                OtpVerificationActivity.class
        );

        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("mobile", mobile);

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}