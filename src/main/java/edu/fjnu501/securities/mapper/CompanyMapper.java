package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {

    //插入新公司
    int insertCompany(Stock company);
    //删除公司
    boolean deleteCompany(int companyId);
    //获取所有公司
    List<Stock> getAllCompanies();
    //更新总资产
    void updateAsset(@Param("asset") double asset, @Param("companyId") int companyId);

    void updatePerPrice(@Param("price") double price, @Param("companyId") int companyId);

    void updateCompanyBasicInfo(Stock stock);

    void updateStockAll(@Param("stockAll") int stockAll, @Param("companyId") int companyId);

    Stock getCompanyInfoByStockId(int companyId);

}