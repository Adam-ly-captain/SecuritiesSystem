package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.StockHolder;
import edu.fjnu501.securities.service.StockService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/get/all")
    @ResponseBody
    public Result getStockInfo() {
        List<Stock> allStockInfo = stockService.getAllStockInfo();
        if (allStockInfo != null) {
            return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", allStockInfo);
        }
        return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
    }

    @RequestMapping(value = "/get/{uid}")
    @ResponseBody
    public Result getStockSumByUid(@PathVariable("uid") int uid) {
        List<StockHolder> stockSumByUid = null;
        try {
            stockSumByUid = stockService.getStockSumByUid(uid);
        } catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", stockSumByUid);
    }

}
