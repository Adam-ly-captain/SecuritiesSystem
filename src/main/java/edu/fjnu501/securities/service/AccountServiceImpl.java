package edu.fjnu501.securities.service;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.User;
import edu.fjnu501.securities.mapper.AccountMapper;
import edu.fjnu501.securities.state.UserType;
import edu.fjnu501.securities.utils.MD5Password;
import edu.fjnu501.securities.utils.RandomUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public User getUserByUsername(String username, String type) {
        if (UserType.Admin.getType().equals(type)) {
            accountMapper.getAdminByUsername(username);
        } else {
            accountMapper.getClientByUsername(username);
        }
        return null;
    }

    @Override
    public String registerClient(Client client) {
        if (!checkIdNumber(client.getIdNumber())) {
            throw new RuntimeException("最多只能拥有一个账号");
        }

        String randomUserName = null;
        try {
            randomUserName = RandomUsername.getRandomUserName(accountMapper.getMaxClientId());
        } catch (Exception e) {
            return null;
        }
        client.setUsername(randomUserName);
        client.setPassword(MD5Password.MD5Pwd(client));
        accountMapper.registerClient(client);

        return randomUserName;
    }

    private boolean checkIdNumber(String id) {
        String[] allClientIdNumber = accountMapper.getAllClientIdNumber();
        for (String idNumber : allClientIdNumber) {
            if (idNumber != null && idNumber.equals(id)) {
                return false;  // ID重复
            }
        }
        return true;
    }

}
