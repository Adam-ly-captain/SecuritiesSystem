package edu.fjnu501.securities.service;


import edu.fjnu501.securities.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CompanyService {

    int addCompany(Company company);

    boolean deleteCompany(int Companyid);

    List<Company> queryCompanysByArray(int currPage, int pageSize);

    void updateCompany(int id,double amount);

}