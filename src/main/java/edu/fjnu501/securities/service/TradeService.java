package edu.fjnu501.securities.service;

import edu.fjnu501.securities.domain.*;
import edu.fjnu501.rpc.domain.BankTrade;

import java.util.List;

public interface TradeService {

    void getTradeByPage(Page page);

    boolean buyStock(Trade trade) throws IllegalAccessException;

    boolean saleStock(Trade trade) throws IllegalAccessException;

    Trade getStockSumByCompanyIdAndStockHolderId(int companyId, int stockHolderId);

    void addMoneyByBankCard(BankTrade bankTrade);

    List<BankTrade> getLimitedBankTrade(int amount);

    void updateBankTradeState(int orderId, int state);

    void addBankCardTradeInfo(BankTrade bankTrade);

    void updateTimeoutTrade(int tradeId);

}
