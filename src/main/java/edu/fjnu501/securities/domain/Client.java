package edu.fjnu501.securities.domain;

public class Client extends User{

    private int uid;
    private String clientName;
    private String clientTelephone;
    private String idNumber;
    private double asset;

    public double getAsset() {
        return asset;
    }

    public void setAsset(double asset) {
        this.asset = asset;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientTelephone() {
        return clientTelephone;
    }

    public void setClientTelephone(String clientTelephone) {
        this.clientTelephone = clientTelephone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

}
