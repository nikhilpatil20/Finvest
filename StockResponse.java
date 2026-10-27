package com.example.finvest;

import com.google.gson.annotations.SerializedName;

public class StockResponse {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("price")
    private double price;

    @SerializedName("change")
    private double change;

    @SerializedName("change_percent")
    private double changePercent;

    @SerializedName("name")
    private String name;

    public StockResponse(String symbol, double price, double change, double changePercent) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.changePercent = changePercent;
    }


    public double getPrice() {
        return price;
    }

    public double getChangePercent() {
        return changePercent;
    }

}