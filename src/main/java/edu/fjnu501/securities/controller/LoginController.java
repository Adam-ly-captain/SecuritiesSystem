package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.service.AccountService;
import edu.fjnu501.securities.shiro.UserToken;
import edu.fjnu501.securities.state.ResultCodeState;
import edu.fjnu501.securities.state.UserType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    private AccountService accountService;

    private int uid;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UserToken token = new UserToken(user.getUsername(), user.getPassword(), user.getType());
        try {
            subject.login(token);
            if (UserType.Admin.getType().equals(user.getType())) {
                uid = 1;
            } else {
                uid = accountService.getUidByUsername(user.getUsername());
            }
        } catch (UnknownAccountException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (ExcessiveAttemptsException e) {
            return new Result(ResultCodeState.FAILED.getState(), e.getMessage(), null);
        } catch (IncorrectCredentialsException e) {
            return new Result(ResultCodeState.PASSWORD.getState(), e.getMessage(), null);
        } catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), "登录失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "登录成功", uid);
    }

    @RequestMapping(value = "/unlogin")
    @ResponseBody
    public Result unLogin() {
        return new Result(ResultCodeState.UNLOGIN.getState(), "请先登录", null);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            return new Result(500, "登出失败", null);
        }
        return new Result(200, "成功登出", null);
    }

}
