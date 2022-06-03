package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.service.AccountService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/get/{uid}")
    @ResponseBody
    public Result getStockHolderInfoByUid(@PathVariable("uid") int uid) {
        Client stockHolderInfoByUid = null;
        try {
            stockHolderInfoByUid = accountService.getStockHolderInfoByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", stockHolderInfoByUid);
    }

    @RequestMapping(value = "/bind")
    @ResponseBody
    public Result bindBankAccount(String username, int stockHolderId) {
        try {
            accountService.bindBankAccount(username, stockHolderId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "绑定失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "绑定成功", null);
    }

    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public Result unauthorized() {
        return new Result(ResultCodeState.UNAUTHORIZED.getState(), "权限不足", null);
    }

}
