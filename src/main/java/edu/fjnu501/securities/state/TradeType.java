package edu.fjnu501.securities.state;

public enum TradeType {

    SALE(1), BUY(0), SAVE(2), WITHDRAW(3);

    private int tradeType;

    TradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getTradeType() {
        return this.tradeType;
    }

}
