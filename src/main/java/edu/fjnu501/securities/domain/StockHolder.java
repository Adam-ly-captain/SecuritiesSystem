package edu.fjnu501.securities.domain;

public class StockHolder {

    private int id;
    private int sid;
    private int cid;
    private int stockSum;

    @Override
    public String toString() {
        return "StockHolder{" +
                "id=" + id +
                ", sid=" + sid +
                ", cid=" + cid +
                ", stockSum=" + stockSum +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getStockSum() {
        return stockSum;
    }

    public void setStockSum(int stockSum) {
        this.stockSum = stockSum;
    }
}
