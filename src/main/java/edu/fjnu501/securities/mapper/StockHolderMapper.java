package edu.fjnu501.securities.mapper;

import org.apache.ibatis.annotations.Param;

public interface StockHolderMapper {

    void updateAsset(@Param("asset") double asset, @Param("stockHolderId") int stockHolderId);

    void updateStockSum(@Param("stockSum") int stockSum, @Param("companyId") int companyId, @Param("stockHolderId") int stockHolderId);

    void deleteStockSumInfo(int stockSumId);

}
