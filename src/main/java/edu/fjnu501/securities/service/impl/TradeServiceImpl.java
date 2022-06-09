package edu.fjnu501.securities.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.securities.domain.*;
import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.securities.mapper.StockHolderMapper;
import edu.fjnu501.securities.mapper.TradeMapper;
import edu.fjnu501.securities.rpc.TaskQueueSchedule;
import edu.fjnu501.securities.service.AccountService;
import edu.fjnu501.securities.service.CompanyService;
import edu.fjnu501.securities.service.StockHolderService;
import edu.fjnu501.securities.service.TradeService;
import edu.fjnu501.securities.state.TradeState;
import edu.fjnu501.securities.state.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tradeService")
public class TradeServiceImpl implements TradeService {

    private double newAsset;
    private int newStockAll;
    private double price;
    private List<Trade> allTradeByUid;
    private double cost;
    private Stock companyInfoByStockId;
    private Trade sum;

    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StockHolderService stockHolderService;
    @Autowired
    private AccountService accountService;

    @Override
    public void getTradeByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        allTradeByUid = tradeMapper.getAllTradeByUid(page.getSid());
        PageInfo<Trade> pageInfo = new PageInfo<>(allTradeByUid);
        page.setData(allTradeByUid);
        page.setTotalPages(pageInfo.getPages());
    }

    @Override
    public boolean buyStock(Trade trade) throws IllegalAccessException {
        trade.setTradeType(TradeType.BUY.getTradeType());
        if (!updateAllInfo(trade)) {
            return false;
        }
        return true;
    }

    private boolean updateAllInfo(Trade trade) throws IllegalAccessException {
        if (!updateCompanyInfo(trade)) {
            return false;
        }
        updateStockHolderInfo(trade);
        tradeMapper.setTradeInfo(trade);
        return true;
    }

    private boolean updateCompanyInfo(Trade trade) {
        companyInfoByStockId = companyService.getCompanyInfoByStockId(trade.getStockId());
        if (companyInfoByStockId != null) {
            trade.setCurrentPerPrice(companyInfoByStockId.getStockPerPrice());
            cost = companyInfoByStockId.getStockPerPrice() * trade.getStockNum();  // 用户交易总金额
            trade.setSumMoney(cost);
            if (trade.getTradeType() == TradeType.BUY.getTradeType()) {
                newAsset = companyInfoByStockId.getAsset() + cost;
                newStockAll = trade.getStockNum() + companyInfoByStockId.getStockAll();
            } else {
                newAsset = companyInfoByStockId.getAsset() - cost;
                newStockAll = companyInfoByStockId.getStockAll();
            }
            price = newAsset / newStockAll;

            companyService.updatePerPrice(price, trade.getStockId());
            companyService.updateAsset(newAsset, trade.getStockId());
            companyService.updateStockAll(newStockAll, trade.getStockId());
        } else {
            return false;
        }
        return true;
    }

    private boolean checkTradeInfo(int companyId, int stockHolderId) {
        sum = getStockSumByCompanyIdAndStockHolderId(companyId, stockHolderId);
        if (sum != null) {
            return true;  // 购买过该支股票
        } else {
            return false;
        }
    }

    private void updateStockHolderInfo(Trade trade) throws IllegalAccessException {
        Client stockHolderInfoByUid = accountService.getStockHolderInfoByUid(trade.getStockHolderId());
        if (trade.getTradeType() == TradeType.BUY.getTradeType()) {
            newAsset = - cost;
            newStockAll = trade.getStockNum();
        } else {
            newAsset = cost;
            newStockAll = - trade.getStockNum();
        }
        stockHolderService.updateAsset(newAsset + stockHolderInfoByUid.getAsset(), trade.getStockHolderId());
        if (checkTradeInfo(trade.getStockId(), trade.getStockHolderId())) {  // 检查是否有购买过该股票
            if (trade.getTradeType() == TradeType.SALE.getTradeType() && trade.getStockNum() > sum.getStockNum()) {
                throw new IllegalAccessException("股数不足");
            }
            if (trade.getStockNum() == sum.getStockNum()) {
                stockHolderService.deleteStockSumInfo(sum.getTradeId());
            }
            stockHolderService.updateStockSum(newStockAll, trade.getStockHolderId(), trade.getStockId());
        } else {
            if (trade.getTradeType() == TradeType.SALE.getTradeType()) {
                throw new RuntimeException("未购买该股票");
            }
            tradeMapper.addStockSumInfo(trade);
        }
    }

    @Override
    public boolean saleStock(Trade trade) throws IllegalAccessException {
        trade.setTradeType(TradeType.SALE.getTradeType());
        if (!updateAllInfo(trade)) {
            return false;
        }
        return true;
    }

    @Override
    public Trade getStockSumByCompanyIdAndStockHolderId(int companyId, int stockHolderId) {
        return tradeMapper.getStockSumByCompanyIdAndStockHolderId(companyId, stockHolderId);
    }

    @Override
    public void addMoneyByBankCard(BankTrade bankTrade) {
        Client stockHolderInfoByUid = accountService.getStockHolderInfoByUid(bankTrade.getStockHolderId());
        if (TradeType.WITHDRAW.getTradeType() == bankTrade.getType()) {  // 取钱
            newAsset = stockHolderInfoByUid.getAsset() + bankTrade.getAddedMoney();
        } else if (TradeType.SAVE.getTradeType() == bankTrade.getType()) {  // 存钱
            newAsset = stockHolderInfoByUid.getAsset() - bankTrade.getAddedMoney();
        } else {
            throw new RuntimeException("不存在该操作");
        }
        stockHolderService.updateAsset(newAsset, bankTrade.getStockHolderId());
    }

    @Override
    public List<BankTrade> getLimitedBankTrade(int amount) {
        return tradeMapper.getLimitedBankTrade(amount);
    }

    @Override
    public void updateBankTradeState(int orderId, int state) {
        tradeMapper.updateBankTradeState(orderId, state);
    }

    @Override
    public void addBankCardTradeInfo(BankTrade bankTrade) {
        tradeMapper.addBankCardTradeInfo(bankTrade);
    }

    @Override
    public void updateTimeoutTrade(int tradeId) {
        tradeMapper.updateBankTradeState(tradeId, TradeState.UNFINISHED.getState());
    }

    @Override
    public List<BankTrade> getAllBankTrade(int sid) {
        return tradeMapper.getAllBankTrade(sid);
    }

    @Override
    public void getBankTradesByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<BankTrade> allBankTrade = getAllBankTrade(page.getSid());
        PageInfo<BankTrade> pageInfo = new PageInfo<>(allBankTrade);
        page.setData(allBankTrade);
        page.setTotalPages(pageInfo.getPages());
    }

}
