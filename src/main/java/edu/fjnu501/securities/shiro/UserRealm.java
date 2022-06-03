package edu.fjnu501.securities.shiro;

import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.service.AccountService;
import edu.fjnu501.securities.state.UserType;
import edu.fjnu501.securities.utils.MD5Password;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = (User) authenticationToken.getPrincipal();
        User userByUsername = accountService.getUserByUsername(user.getUsername(), user.getType());

        if (userByUsername == null) {
            throw new UnknownAccountException("账号不存在");
        }

        // 用来判断之前保存的token中的账号密码是否正确
        AuthenticationInfo info = new SimpleAuthenticationInfo(userByUsername, userByUsername.getPassword(), ByteSource.Util.bytes(userByUsername.getUsername() + MD5Password.getShiroSalt()), getName());
        return info;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> role = new HashSet<>(1);
        if ("root".equals(user.getUsername())) {
            role.add("admin");
        } else {
            role.add("client");
        }
        authorizationInfo.setRoles(role);
        return authorizationInfo;
    }
}
