package edu.fjnu501.securities.service;

import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.StockHolder;

import java.util.List;

public interface StockService {

    List<Stock> getAllStockInfo();

    List<StockHolder> getStockSumByUid(int uid);

    void getStockSumByPage(Page page);

}
