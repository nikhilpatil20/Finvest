package com.example.finvest;

public class DashboardModel {

    private String userName;
    private double portfolioValue;
    private double investedValue;
    private double profitLoss;
    private double profitLossPercentage;

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