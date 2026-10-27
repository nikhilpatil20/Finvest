package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;
import java.util.Random;

public class OtpVerificationActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvOtpEmail;
    private EditText etOtp;
    private TextView tvTimer;
    private TextView tvResendOtp;
    private TextView tvDemoOtp;
    private MaterialButton btnVerifyOtp;

    private CountDownTimer countDownTimer;

    private String generatedOtp;
    private String userEmail;

    private static final long OTP_TIME = 120000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_verification);

        initializeViews();

        userEmail = getIntent().getStringExtra("email");

        if (userEmail == null || userEmail.isEmpty()) {
            userEmail = "your registered email";
        }

        tvOtpEmail.setText(userEmail);

        generateOtp();
        startOtpTimer();

        setupClickListeners();
    }

    private void initializeViews() {

        btnBack = findViewById(R.id.btnBack);
        tvOtpEmail = findViewById(R.id.tvOtpEmail);
        etOtp = findViewById(R.id.etOtp);
        tvTimer = findViewById(R.id.tvTimer);
        tvResendOtp = findViewById(R.id.tvResendOtp);
        tvDemoOtp = findViewById(R.id.tvDemoOtp);
        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);
    }

    private void setupClickListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnVerifyOtp.setOnClickListener(v -> verifyOtp());

        tvResendOtp.setOnClickListener(v -> {

            if (tvResendOtp.isEnabled()) {

                generateOtp();

                etOtp.setText("");

                startOtpTimer();

                Toast.makeText(
                        this,
                        "New OTP generated",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void generateOtp() {

        Random random = new Random();

        generatedOtp = String.format(
                Locale.getDefault(),
                "%06d",
                random.nextInt(1000000)
        );

        // DEVELOPMENT ONLY
        // Production मध्ये OTP screen वर कधीही दाखवू नये
        tvDemoOtp.setText(
                "Development OTP: " + generatedOtp
        );
    }

    private void startOtpTimer() {

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        tvResendOtp.setEnabled(false);
        tvResendOtp.setAlpha(0.5f);

        countDownTimer = new CountDownTimer(
                OTP_TIME,
                1000
        ) {

            @Override
            public void onTick(long millisUntilFinished) {

                long seconds =
                        millisUntilFinished / 1000;

                long minutes = seconds / 60;

                seconds = seconds % 60;

                String time = String.format(
                        Locale.getDefault(),
                        "OTP expires in %02d:%02d",
                        minutes,
                        seconds
                );

                tvTimer.setText(time);
            }

            @Override
            public void onFinish() {

                tvTimer.setText("OTP expired");

                tvResendOtp.setEnabled(true);
                tvResendOtp.setAlpha(1f);
            }
        };

        countDownTimer.start();
    }

    private void verifyOtp() {

        String enteredOtp = etOtp.getText()
                .toString()
                .trim();

        if (enteredOtp.isEmpty()) {

            etOtp.setError("Enter OTP");
            return;
        }

        if (enteredOtp.length() != 6) {

            etOtp.setError("OTP must be 6 digits");
            return;
        }

        if (enteredOtp.equals(generatedOtp)) {

            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            Toast.makeText(
                    this,
                    "OTP verified successfully",
                    Toast.LENGTH_SHORT
            ).show();

            String flow = getIntent()
                    .getStringExtra("flow");

            if ("forgot_password".equals(flow)) {

                Intent intent = new Intent(
                        OtpVerificationActivity.this,
                        ResetPasswordActivity.class
                );

                intent.putExtra("email", userEmail);

                startActivity(intent);

                finish();

            } else {

                Intent intent = new Intent(
                        OtpVerificationActivity.this,
                        LoginActivity.class
                );

                intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK
                );

                startActivity(intent);

                finish();
            }

        } else {

            etOtp.setError("Invalid OTP");
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}