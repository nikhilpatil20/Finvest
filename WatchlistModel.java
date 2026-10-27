package com.example.finvest;

public class WatchlistModel {

    private final String symbol;
    private final String companyName;
    private final double price;
    private final double changePercentage;

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

    public double getChangePercentage() {
        return changePercentage;
    }
}