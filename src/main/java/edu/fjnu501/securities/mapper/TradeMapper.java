package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Trade;

import java.util.List;

public interface TradeMapper {

    List<Trade> getAllTradeByUid(int uid);

}
