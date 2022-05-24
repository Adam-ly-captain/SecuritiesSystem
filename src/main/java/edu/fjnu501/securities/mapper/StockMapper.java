package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.StockHolder;

import java.util.List;

public interface StockMapper {

    List<Stock> getAllStockInfo();

    List<StockHolder> getStockSumByUid(int uid);

}
