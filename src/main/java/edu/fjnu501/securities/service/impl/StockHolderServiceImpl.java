package edu.fjnu501.securities.service.impl;

import edu.fjnu501.securities.mapper.StockHolderMapper;
import edu.fjnu501.securities.service.StockHolderService;
import edu.fjnu501.securities.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stockHolderService")
public class StockHolderServiceImpl implements StockHolderService {

    @Autowired
    private StockHolderMapper stockHolderMapper;

    @Override
    public void updateAsset(double asset, int stockHolderId) {
        stockHolderMapper.updateAsset(asset, stockHolderId);
    }

    @Override
    public void updateStockSum(int stockSum, int stockHolderId, int companyId) {
        stockHolderMapper.updateStockSum(stockSum, companyId, stockHolderId);
    }

}
