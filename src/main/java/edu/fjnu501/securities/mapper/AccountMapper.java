package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.User;

public interface AccountMapper {

    User getAdminByUsername(String username);

    User getClientByUsername(String username);

    int getMaxClientId();

    void registerClient(Client client);

    String[] getAllClientIdNumber();

}
