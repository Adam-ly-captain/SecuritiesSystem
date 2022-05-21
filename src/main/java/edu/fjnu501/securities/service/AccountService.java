package edu.fjnu501.securities.service;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.User;

public interface AccountService {

    User getUserByUsername(String username, String type);

    String registerClient(Client client);

}
