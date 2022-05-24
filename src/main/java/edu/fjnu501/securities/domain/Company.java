package edu.fjnu501.securities.domain;

public class Company {

    private int companyId;

    private String companyName;

    private int companyStockId;

    private String stockName;
    //股票单价
    private double companyStockPerprice;
    //公司资产
    private double companyAsset;
    //公司持有的股票数
    private int companyStockNum;

    public Company() {
    }

    public Company(int companyId, String companyName, int companyStockId, String stockName, double companyStockPerprice, double companyAsset, int companyStockNum) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyStockId = companyStockId;
        this.stockName = stockName;
        this.companyStockPerprice = companyStockPerprice;
        this.companyAsset = companyAsset;
        this.companyStockNum = companyStockNum;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyStockId() {
        return companyStockId;
    }

    public void setCompanyStockId(int companyStockId) {
        this.companyStockId = companyStockId;
    }

    public double getCompanyStockPerprice() {
        return companyStockPerprice;
    }

    public void setCompanyStockPerprice(double companyStockPerprice) {
        this.companyStockPerprice = companyStockPerprice;
    }

    public double getCompanyAsset() {
        return companyAsset;
    }

    public void setCompanyAsset(double companyAsset) {
        this.companyAsset = companyAsset;
    }

    public int getCompanyStockNum() {
        return companyStockNum;
    }

    public void setCompanyStockNum(int companyStockNum) {
        this.companyStockNum = companyStockNum;
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

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyStockId=" + companyStockId +
                ", stockName='" + stockName + '\'' +
                ", companyStockPerprice=" + companyStockPerprice +
                ", companyAsset=" + companyAsset +
                ", companyStockNum=" + companyStockNum +
                '}';
    }
}