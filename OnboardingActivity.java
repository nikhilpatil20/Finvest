package com.example.finvest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView tvSkip;
    private Button btnNext;

    private OnboardingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        tvSkip = findViewById(R.id.tvSkip);
        btnNext = findViewById(R.id.btnNext);

        adapter = new OnboardingAdapter();

        viewPager.setAdapter(adapter);

        btnNext.setOnClickListener(v -> {

            int currentItem = viewPager.getCurrentItem();

            if (currentItem < adapter.getItemCount() - 1) {

                viewPager.setCurrentItem(currentItem + 1);

            } else {

                openLogin();

            }
        });

        tvSkip.setOnClickListener(v -> openLogin());

        viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(int position) {

                        super.onPageSelected(position);

                        if (position ==
                                adapter.getItemCount() - 1) {

                            btnNext.setText("Get Started");

                        } else {

                            btnNext.setText("Next");
                        }
                    }
                }
        );
    }

    private void openLogin() {

        Intent intent = new Intent(
                OnboardingActivity.this,
                LoginActivity.class
        );

        startActivity(intent);

        finish();
    }
}