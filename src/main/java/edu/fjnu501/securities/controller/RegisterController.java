package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.service.AccountService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/client")
    @ResponseBody
    public Result register(@RequestBody Client client) {
        String username = null;
        try {
            username = accountService.registerClient(client);
        } catch (RuntimeException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), "注册失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "注册成功", username);
    }

}
