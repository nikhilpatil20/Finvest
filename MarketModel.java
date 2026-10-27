package com.example.finvest;

public class MarketModel {

    private String symbol;
    private String name;
    private double value;
    private double change;
    private double changePercentage;

    public MarketModel(
            String symbol,
            String name,
            double value,
            double change,
            double changePercentage
    ) {
        this.symbol = symbol;
        this.name = name;
        this.value = value;
        this.change = change;
        this.changePercentage = changePercentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public double getChange() {
        return change;
    }

    public double getChangePercentage() {
        return changePercentage;
    }
}