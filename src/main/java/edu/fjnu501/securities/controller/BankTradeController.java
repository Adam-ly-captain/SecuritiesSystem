package edu.fjnu501.securities.controller;

import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.rpc.TaskQueueSchedule;
import edu.fjnu501.securities.service.TradeService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/trade/bank")
public class BankTradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addAsset(@RequestBody BankTrade bankTrade) {
        try {
            tradeService.addBankCardTradeInfo(bankTrade);
        } catch (Exception e) {
            e.printStackTrace();
            unlock();
            return new Result(ResultCodeState.FAILED.getState(), "交易失败", null);
        }
        unlock();
        return new Result(ResultCodeState.SUCCESS.getState(), "交易成功", null);
    }

    @RequestMapping(value = "/get/page")
    @ResponseBody
    public Result getBankTradesInfoByPage(@RequestBody Page page) {
        try {
            tradeService.getBankTradesByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "获取分页数据失败", null);
        }
        return new Result(ResultCodeState.FAILED.getState(), "获取分页数据成功", page);
    }

    @RequestMapping(value = "/update/{tid}")
    @ResponseBody
    public Result updateTimeoutTrade(@PathVariable("tid") int tradeId) {
        try {
            tradeService.updateTimeoutTrade(tradeId);
        } catch (Exception e) {
            e.printStackTrace();
            unlock();
            return new Result(ResultCodeState.FAILED.getState(), "重新提交订单失败", null);
        }
        unlock();
        return new Result(ResultCodeState.SUCCESS.getState(), "重新提交订单成功", null);
    }

    private void unlock() {
        TaskQueueSchedule.lock.lock();
        TaskQueueSchedule.wait.signalAll();
        TaskQueueSchedule.lock.unlock();
    }

}
