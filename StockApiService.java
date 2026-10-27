package com.example.finvest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StockApiService {

    @GET("stock/{symbol}")
    Call<StockResponse> getStock(
            @Path("symbol") String symbol

    );
}