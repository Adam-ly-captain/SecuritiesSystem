package edu.fjnu501.securities.domain;

public class Page {

    private int sid;
    private int pageNum;
    private int pageSize;
    private Object data;
    private int totalPages;

    @Override
    public String toString() {
        return "Page{" +
                "sid=" + sid +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", data=" + data +
                ", totalPages=" + totalPages +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
