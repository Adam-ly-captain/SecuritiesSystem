package edu.fjnu501.securities.state;

public enum UserType {

    Admin("1"), Client("0");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
