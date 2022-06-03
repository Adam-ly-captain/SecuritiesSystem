package edu.fjnu501.securities.mapper;

import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.Trade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeMapper {

    List<Trade> getAllTradeByUid(int uid);

    void setTradeInfo(Trade trade);

    Trade getStockSumByCompanyIdAndStockHolderId(@Param("companyId") int companyId, @Param("stockHolderId") int stockHolderId);

    void addStockSumInfo(Trade trade);

    List<BankTrade> getLimitedBankTrade(int amount);

    void updateBankTradeState(@Param("orderId") int orderId, @Param("state") int state);

    void addBankCardTradeInfo(BankTrade bankTrade);

}
