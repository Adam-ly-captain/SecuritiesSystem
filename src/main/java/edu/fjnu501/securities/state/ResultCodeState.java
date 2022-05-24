package edu.fjnu501.securities.state;

public enum ResultCodeState {

    SUCCESS(200), INVALID(400), FAILED(500), PASSWORD(405);

    private int state;

    ResultCodeState(int code) {
        this.state = code;
    }

    public int getState() {
        return state;
    }

}
