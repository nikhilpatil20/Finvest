package com.example.finvest;

public class WatchlistModel {

    private String symbol;
    private String companyName;
    private double price;
    private double change;
    private double changePercentage;

    public WatchlistModel(
            String symbol,
            String companyName,
            double price,
            double change,
            double changePercentage
    ) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.change = change;
        this.changePercentage = changePercentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public double getChange() {
        return change;
    }

    public double getChangePercentage() {
        return changePercentage;
    }
}