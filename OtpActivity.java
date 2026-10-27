package com.example.finvest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class OtpActivity extends AppCompatActivity {

    private EditText etOtp;

    private TextView tvTimer;
    private TextView tvResend;

    private Button btnVerify;

    private String generatedOtp;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);

        initializeViews();

        generateOtp();

        startOtpTimer();

        setupClickListeners();
    }

    private void initializeViews() {

        etOtp = findViewById(R.id.etOtp);

        tvTimer = findViewById(R.id.tvTimer);
        tvResend = findViewById(R.id.tvResend);

        btnVerify = findViewById(R.id.btnVerify);
    }

    private void setupClickListeners() {

        btnVerify.setOnClickListener(v -> verifyOtp());

        tvResend.setOnClickListener(v -> {

            if (tvResend.isEnabled()) {

                generateOtp();

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
                random.nextInt(1_000_000)
        );

        // Testing purpose only
        Toast.makeText(
                this,
                "Your OTP: " + generatedOtp,
                Toast.LENGTH_LONG
        ).show();
    }

    private void verifyOtp() {

        String enteredOtp =
                etOtp.getText().toString().trim();

        if (enteredOtp.isEmpty()) {

            etOtp.setError("Enter OTP");

            return;
        }

        if (enteredOtp.length() != 6) {

            etOtp.setError("Enter valid 6-digit OTP");

            return;
        }

        if (enteredOtp.equals(generatedOtp)) {

            Toast.makeText(
                    this,
                    "OTP Verified Successfully 🎉",
                    Toast.LENGTH_SHORT
            ).show();

            // Next screen later
            // openHome();

        } else {

            Toast.makeText(
                    this,
                    "Invalid OTP",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void startOtpTimer() {

        tvResend.setEnabled(false);

        if (countDownTimer != null) {

            countDownTimer.cancel();
        }

        countDownTimer =
                new CountDownTimer(60000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        long seconds =
                                millisUntilFinished / 1000;

                        tvTimer.setText(
                                "Resend OTP in " +
                                        seconds +
                                        " seconds"
                        );
                    }

                    @Override
                    public void onFinish() {

                        tvTimer.setText(
                                "Didn't receive OTP?"
                        );

                        tvResend.setEnabled(true);
                    }
                };

        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {

            countDownTimer.cancel();
        }
    }
}