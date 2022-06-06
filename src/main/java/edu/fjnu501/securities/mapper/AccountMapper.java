package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Client;
import edu.fjnu501.securities.domain.User;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    User getAdminByUsername(String username);

    User getClientByUsername(String username);

    int getMaxClientId();

    void registerClient(Client client);

    String[] getAllClientIdNumber();

    void bindBankAccount(@Param("username") String username, @Param("stockHolderId") int stockHolderId);

    Client getStockHolderInfoByUid(int uid);

    int getUidByUsername(String username);

}
