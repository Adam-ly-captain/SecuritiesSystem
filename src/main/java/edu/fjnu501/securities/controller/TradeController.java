package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.service.TradeService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/page")
    @ResponseBody
    public Result getTradeByPage(@RequestBody Page page) {
        try {
            tradeService.getTradeByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "获取分页数据失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取分页数据成功", page);
    }

}
