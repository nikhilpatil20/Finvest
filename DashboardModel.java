package com.example.finvest;

public class DashboardModel {

    private final String userName;
    private final double portfolioValue;
    private final double investedValue;
    private final double profitLoss;
    private final double profitLossPercentage;

    public DashboardModel(
            String userName,
            double portfolioValue,
            double investedValue,
            double profitLoss,
            double profitLossPercentage
    ) {
        this.userName = userName;
        this.portfolioValue = portfolioValue;
        this.investedValue = investedValue;
        this.profitLoss = profitLoss;
        this.profitLossPercentage = profitLossPercentage;
    }

    public String getUserName() {
        return userName;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public double getInvestedValue() {
        return investedValue;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public double getProfitLossPercentage() {
        return profitLossPercentage;
    }
}