package edu.fjnu501.securities.test;

import edu.fjnu501.securities.domain.Admin;
import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.mapper.AccountMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void test1() {
        Admin admin = new Admin();
        if (admin instanceof User) {
            System.out.println(admin.getPassword());
        }
    }

    @Test
    public void test2() {
        System.out.println(accountMapper.getMaxClientId());
    }

}
