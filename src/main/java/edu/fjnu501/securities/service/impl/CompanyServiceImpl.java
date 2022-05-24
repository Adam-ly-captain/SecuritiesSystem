package edu.fjnu501.securities.service.impl;

import edu.fjnu501.securities.domain.Company;
import edu.fjnu501.securities.mapper.CompanyMapper;
import edu.fjnu501.securities.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int addCompany(Company company) {
        int rows = companyMapper.insertCompany(company);
        return rows;
    }

    @Override
    public boolean deleteCompany(int Companyid) {

        boolean flag = companyMapper.deleteCompany(Companyid);
        return flag;
    }

    @Override
    public List<Company> queryCompanysByArray(int currPage, int pageSize) {
        List<Company> companies = companyMapper.queryCompanysByArray();
//        从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
//        到第几条数据结束
        int lastIndex = currPage * pageSize;
        return companies.subList(firstIndex, lastIndex);

    }

    @Override
    public void updateCompany(int id,double amount) {

        Company company=new Company();
        company.setCompanyId(id);
        company.setCompanyAsset(amount);
        companyMapper.updateCompany(company);

    }

}