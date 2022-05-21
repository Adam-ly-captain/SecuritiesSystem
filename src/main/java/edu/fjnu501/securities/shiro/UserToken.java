package edu.fjnu501.securities.shiro;

import edu.fjnu501.securities.domain.User;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class UserToken extends UsernamePasswordToken implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 4812793519945855483L;

    // 手机号码
    private String username;
    private String type;

    /**
     * 重写getPrincipal方法
     */
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername(username);
        user.setType(type);
        return user;
    }


    public UserToken() {
        // TODO Auto-generated constructor stub
    }

    public UserToken(final String username) {
        // TODO Auto-generated constructor stub
        this.username = username;
    }

    public UserToken(final String userName, final String password) {
        // TODO Auto-generated constructor stub
        super(userName, password);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setType(String type) {
        this.type = type;
    }
}
