package edu.fjnu501.securities.service;


import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CompanyService {

    void addCompany(Stock stock);

    boolean deleteCompany(int companyId);

    void getAllCompaniesByPage(Page page);

    void updateAsset(double asset, int companyId);

    void updatePerPrice(double price, int companyId);

    void updateCompanyBasicInfo(Stock stock);

    void updateStockAll(int stockAll, int companyId);

    Stock getCompanyInfoByStockId(int companyId);

}