package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.shiro.UserToken;
import edu.fjnu501.securities.state.ResultCodeState;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UserToken token = new UserToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        }
        catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), "登录失败", null);
        }
        return null;
    }

}
