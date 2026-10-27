package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;

    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;

    private MaterialButton btnResetPassword;

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);

        userEmail = getIntent()
                .getStringExtra("email");

        initializeViews();

        btnResetPassword.setOnClickListener(
                v -> resetPassword()
        );
    }

    private void initializeViews() {

        passwordLayout =
                findViewById(R.id.passwordLayout);

        confirmPasswordLayout =
                findViewById(R.id.confirmPasswordLayout);

        etPassword =
                findViewById(R.id.etPassword);

        etConfirmPassword =
                findViewById(R.id.etConfirmPassword);

        btnResetPassword =
                findViewById(R.id.btnResetPassword);
    }

    private void resetPassword() {

        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);

        String password = etPassword
                .getText()
                .toString();

        String confirmPassword = etConfirmPassword
                .getText()
                .toString();

        if (password.isEmpty()) {

            passwordLayout.setError(
                    "Enter new password"
            );

            return;
        }

        if (password.length() < 8) {

            passwordLayout.setError(
                    "Password must contain at least 8 characters"
            );

            return;
        }

        if (confirmPassword.isEmpty()) {

            confirmPasswordLayout.setError(
                    "Confirm your password"
            );

            return;
        }

        if (!password.equals(confirmPassword)) {

            confirmPasswordLayout.setError(
                    "Passwords do not match"
            );

            return;
        }

        /*
         * API will be added later.
         *
         * POST /api/v1/auth/reset-password
         */

        Toast.makeText(
                this,
                "Password reset successfully",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent = new Intent(
                ResetPasswordActivity.this,
                LoginActivity.class
        );

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);

        finish();
    }
}