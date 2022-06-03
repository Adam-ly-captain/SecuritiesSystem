package edu.fjnu501.securities.state;

public enum TradeState {

    TIMEOUT(2), COMPLETED(1), UNFINISHED(0);

    private int state;

    TradeState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
