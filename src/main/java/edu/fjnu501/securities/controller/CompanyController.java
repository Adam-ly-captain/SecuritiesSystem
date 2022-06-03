package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Page;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.service.CompanyService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public Result addCompany(@RequestBody Stock stock) {
        try {
            companyService.addCompany(stock);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "添加失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "添加成功", null);
    }

    @RequestMapping(value = "/delete/{cid}")
    @ResponseBody
    public Result deleteCompany(@PathVariable("cid") int cid) {
        try {
            companyService.deleteCompany(cid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "删除失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "删除成功", null);
    }

    @RequestMapping(value = "/update/name")
    @ResponseBody
    public Result updateCompanyBasicInfo(@RequestBody Stock stock) {
        try {
            companyService.updateCompanyBasicInfo(stock);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "更新失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "更新成功", null);
    }

    @RequestMapping(value = "/get/page")
    @ResponseBody
    public Result getCompaniesInfoByPage(@RequestBody Page page) {
        try {
            companyService.getAllCompaniesByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "获取分页数据失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取分页数据成功", page);
    }

    @RequestMapping(value = "/get/{cid}")
    @ResponseBody
    public Result getCompanyInfoByCompanyId(@PathVariable("cid") int cid) {
        Stock companyInfoByStockId = null;
        try {
            companyInfoByStockId = companyService.getCompanyInfoByStockId(cid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", companyInfoByStockId);
    }

    @RequestMapping(value = "/update/asset")
    @ResponseBody
    public Result updateCompanyAsset(int cid, double asset) {
        try {
            companyService.updateAsset(asset, cid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), "更新失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "更新成功", null);
    }

}
