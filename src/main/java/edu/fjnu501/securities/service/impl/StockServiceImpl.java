package edu.fjnu501.securities.service.impl;

import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.StockHolder;
import edu.fjnu501.securities.mapper.StockMapper;
import edu.fjnu501.securities.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<Stock> getAllStockInfo() {
        return stockMapper.getAllStockInfo();
    }

    @Override
    public List<StockHolder> getStockSumByUid(int uid) {
        return stockMapper.getStockSumByUid(uid);
    }

}
