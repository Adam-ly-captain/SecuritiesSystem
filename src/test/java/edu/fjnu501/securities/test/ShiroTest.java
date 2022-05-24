package edu.fjnu501.securities.test;

import edu.fjnu501.securities.domain.Admin;
import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.mapper.AccountMapper;
import edu.fjnu501.securities.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ShiroTest {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @Test
    public void test1() {
        Admin admin = new Admin();
        if (admin instanceof User) {
            System.out.println(admin.getPassword());
        }
    }

    @Test
    public void test2() {
        System.out.println(accountMapper.getClientByUsername("1"));
    }

}
