package com.example.finvest;

public class MarketModel {

    private final String symbol;
    private final String name;
    private final double value;
    private final double change;
    private final double changePercentage;

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