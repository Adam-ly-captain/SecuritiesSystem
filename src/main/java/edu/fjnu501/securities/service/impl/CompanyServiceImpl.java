package edu.fjnu501.securities.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.mapper.CompanyMapper;
import edu.fjnu501.securities.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public void addCompany(Stock stock) {
        stock.setStockPerPrice(stock.getAsset() / stock.getStockNum());
        stock.setStockAll(stock.getStockNum());
        companyMapper.insertCompany(stock);
    }

    @Override
    public boolean deleteCompany(int companyId) {
        boolean flag = companyMapper.deleteCompany(companyId);
        return flag;
    }

    @Override
    public void getAllCompaniesByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Stock> allCompanies = companyMapper.getAllCompanies();
        PageInfo<Stock> stockPageInfo = new PageInfo<>(allCompanies);
        page.setTotalPages(stockPageInfo.getPages());
        page.setData(allCompanies);
    }

    @Override
    public void updateAsset(double asset, int companyId) {
        Stock companyInfoByStockId = companyMapper.getCompanyInfoByStockId(companyId);
        companyMapper.updatePerPrice(asset / companyInfoByStockId.getStockAll(), companyId);
        companyMapper.updateAsset(asset, companyId);
    }

    @Override
    public void updatePerPrice(double price, int companyId) {
        companyMapper.updatePerPrice(price, companyId);
    }

    @Override
    public void updateCompanyBasicInfo(Stock stock) {
        companyMapper.updateCompanyBasicInfo(stock);
    }

    @Override
    public void updateStockAll(int stockAll, int companyId) {
        companyMapper.updateStockAll(stockAll, companyId);
    }

    @Override
    public Stock getCompanyInfoByStockId(int companyId) {
        return companyMapper.getCompanyInfoByStockId(companyId);
    }

}