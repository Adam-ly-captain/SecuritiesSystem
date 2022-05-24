package edu.fjnu501.securities.mapper;

import edu.fjnu501.securities.domain.Company;

import java.util.List;

public interface CompanyMapper {
    //插入新公司
    int insertCompany(Company company);
    //删除公司
    boolean deleteCompany(int companyid);
    //分页操作
    List<Company> queryCompanysByArray();

    //更新操作
    int updateCompany(Company company);
}