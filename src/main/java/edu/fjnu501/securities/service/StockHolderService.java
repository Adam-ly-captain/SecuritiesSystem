package edu.fjnu501.securities.service;

public interface StockHolderService {

    void updateAsset(double asset, int stockHolderId);

    void updateStockSum(int stockSum, int stockHolderId, int companyId);

    void deleteStockSumInfo(int stockSumId);

}
