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


    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getChange() {
        return change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public String getName() {
        return name;
    }
}