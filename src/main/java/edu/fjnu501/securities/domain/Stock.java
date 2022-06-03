package edu.fjnu501.securities.domain;

public class Stock {

    private int companyId;
    private double stockPerPrice;
    private double asset;
    private int stockNum;
    private String companyName;
    private String stockName;
    private int stockAll;

    @Override
    public String toString() {
        return "Stock{" +
                "companyId=" + companyId +
                ", stockPerPrice=" + stockPerPrice +
                ", asset=" + asset +
                ", stockNum=" + stockNum +
                ", companyName='" + companyName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockAll=" + stockAll +
                '}';
    }

    public int getStockAll() {
        return stockAll;
    }

    public void setStockAll(int stockAll) {
        this.stockAll = stockAll;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public double getStockPerPrice() {
        return stockPerPrice;
    }

    public void setStockPerPrice(double stockPerPrice) {
        this.stockPerPrice = stockPerPrice;
    }

    public double getAsset() {
        return asset;
    }

    public void setAsset(double asset) {
        this.asset = asset;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
