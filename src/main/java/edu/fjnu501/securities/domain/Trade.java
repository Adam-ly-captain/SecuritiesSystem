package edu.fjnu501.securities.domain;

public class Trade {

    private int tradeId;
    private int stockHolderId;
    private int stockId;
    private int tradeType;
    private int stockNum;
    private double sumMoney;
    private double currentPerPrice;

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", stockHolderId=" + stockHolderId +
                ", stockId=" + stockId +
                ", tradeType=" + tradeType +
                ", stockNum=" + stockNum +
                ", sumMoney=" + sumMoney +
                ", currentPerPrice=" + currentPerPrice +
                '}';
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getStockHolderId() {
        return stockHolderId;
    }

    public void setStockHolderId(int stockHolderId) {
        this.stockHolderId = stockHolderId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public double getCurrentPerPrice() {
        return currentPerPrice;
    }

    public void setCurrentPerPrice(double currentPerPrice) {
        this.currentPerPrice = currentPerPrice;
    }
}
