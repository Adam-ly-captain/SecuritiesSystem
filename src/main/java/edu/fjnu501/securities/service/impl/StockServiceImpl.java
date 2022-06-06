package edu.fjnu501.securities.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.securities.domain.Page;
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

    @Override
    public void getStockSumByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<StockHolder> stockSumByUid = getStockSumByUid(page.getSid());
        PageInfo<Stock> pageInfo = new PageInfo<>();
        page.setData(stockSumByUid);
        page.setTotalPages(pageInfo.getPages());
    }

}
