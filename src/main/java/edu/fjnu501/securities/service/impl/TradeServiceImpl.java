package edu.fjnu501.securities.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Trade;
import edu.fjnu501.securities.mapper.TradeMapper;
import edu.fjnu501.securities.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tradeService")
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public void getTradeByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Trade> allTradeByUid = tradeMapper.getAllTradeByUid(page.getSid());
        PageInfo<Trade> pageInfo = new PageInfo<>();
        page.setData(allTradeByUid);
        page.setTotalPages(pageInfo.getPages());
    }

}
