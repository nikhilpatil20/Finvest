package com.example.finvest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvPortfolioValue;
    private TextView tvPortfolioProfit;
    private TextView tvInvestedValue;

    private TextView tvNiftyValue;
    private TextView tvNiftyChange;

    private TextView tvSensexValue;
    private TextView tvSensexChange;

    private RecyclerView rvWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initializeViews();

        loadDashboard();

        // API call
        loadLiveMarketData();
    }

    public void setContentView(int activityHome) {
    }

    private void initializeViews() {

        tvUserName = findViewById(R.id.tvUserName);

        tvPortfolioValue =
                findViewById(R.id.tvPortfolioValue);

        tvPortfolioProfit =
                findViewById(R.id.tvPortfolioProfit);

        tvInvestedValue =
                findViewById(R.id.tvInvestedValue);

        tvNiftyValue =
                findViewById(R.id.tvNiftyValue);

        tvNiftyChange =
                findViewById(R.id.tvNiftyChange);

        tvSensexValue =
                findViewById(R.id.tvSensexValue);

        tvSensexChange =
                findViewById(R.id.tvSensexChange);

        rvWatchlist =
                findViewById(R.id.rvWatchlist);
    }

    private void loadDashboard() {

        DashboardModel dashboard =
                new DashboardModel(
                        "Nikhil",
                        125450.00,
                        123000.00,
                        2450.00,
                        1.99
                );

        tvUserName.setText(
                dashboard.getUserName() + " 👋"
        );

        tvPortfolioValue.setText(
                String.format(
                        Locale.getDefault(),
                        "₹%,.2f",
                        dashboard.getPortfolioValue()
                )
        );

        tvPortfolioProfit.setText(
                String.format(
                        Locale.getDefault(),
                        "%+.2f (%+.2f%%)",
                        dashboard.getProfitLoss(),
                        dashboard.getProfitLossPercentage()
                )
        );

        tvInvestedValue.setText(
                String.format(
                        Locale.getDefault(),
                        "Invested ₹%,.2f",
                        dashboard.getInvestedValue()
                )
        );

        // Temporary local data.
        // Later this will also come from our backend API.
        loadWatchlist();
    }

    /**
     * LIVE MARKET API
     */
    private void loadLiveMarketData() {

        StockApiService apiService =
                ApiClient.getApiService();

        // NIFTY
        apiService.getStock("NIFTY")
                .enqueue(new Callback<>() {

                    @Override
                    public void onResponse(
                            @NonNull Call<StockResponse> call,
                            @NonNull Response<StockResponse> response) {

                        if (response.isSuccessful()
                                && response.body() != null) {

                            StockResponse stock =
                                    response.body();

                            tvNiftyValue.setText(
                                    String.format(
                                            Locale.getDefault(),
                                            "%,.2f",
                                            stock.getPrice()
                                    )
                            );

                            tvNiftyChange.setText(
                                    String.format(
                                            Locale.getDefault(),
                                            "%+.2f%%",
                                            stock.getChangePercent()
                                    )
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            @NonNull Call<StockResponse> call,
                            @NonNull Throwable t) {

                        tvNiftyValue.setText("--");
                        tvNiftyChange.setText("--");

                        Toast.makeText(
                                HomeActivity.this,
                                "Unable to load NIFTY data",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });


        // SENSEX
        apiService.getStock("SENSEX")
                .enqueue(new Callback<>() {

                    @Override
                    public void onResponse(
                            @NonNull retrofit2.Call<StockResponse> call,
                            @NonNull retrofit2.Response<StockResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            StockResponse stock = response.body();

                            // JSON response Logcat  दाखवण्यासाठी
                            Log.d(
                                    "API_RESPONSE",
                                    new Gson().toJson(stock)
                            );


                            tvNiftyValue.setText(
                                    String.format(
                                            Locale.getDefault(),
                                            "%,.2f",
                                            stock.getPrice()
                                    )
                            );

                            tvNiftyChange.setText(
                                    String.format(
                                            Locale.getDefault(),
                                            "%+.2f%%",
                                            stock.getChangePercent()
                                    )
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            @NonNull Call<StockResponse> call,
                            @NonNull Throwable t) {

                        tvSensexValue.setText("--");
                        tvSensexChange.setText("--");
                    }
                });
    }

    private void loadWatchlist() {

        List<WatchlistModel> stocks =
                new ArrayList<>();

        stocks.add(
                new WatchlistModel(
                        "RELIANCE",
                        "Reliance Industries",
                        1420.50,
                        16.80,
                        1.20
                )
        );

        stocks.add(
                new WatchlistModel(
                        "TCS",
                        "Tata Consultancy Services",
                        3450.00,
                        27.50,
                        0.80
                )
        );

        stocks.add(
                new WatchlistModel(
                        "INFY",
                        "Infosys",
                        1680.00,
                        -6.70,
                        -0.40
                )
        );

        stocks.add(
                new WatchlistModel(
                        "HDFC BANK",
                        "HDFC Bank",
                        1825.30,
                        14.25,
                        0.79
                )
        );

        WatchlistAdapter adapter =
                new WatchlistAdapter(stocks);

        rvWatchlist.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvWatchlist.setAdapter(adapter);
    }
}