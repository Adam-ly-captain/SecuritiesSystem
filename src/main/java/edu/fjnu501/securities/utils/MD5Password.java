package edu.fjnu501.securities.utils;

import edu.fjnu501.securities.domain.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Password {

    private static String shiroSalt = "securities_system501";

    public static String MD5Pwd(User customer) {
        ByteSource salt = ByteSource.Util.bytes(customer.getUsername() + shiroSalt);
        String password = new SimpleHash("MD5", customer.getPassword(), salt, 328).toHex();
        return password;
    }

    public static String getShiroSalt() {
        return shiroSalt;
    }

}
