package edu.fjnu501.securities.controller;

import edu.fjnu501.securities.domain.Company;
import edu.fjnu501.securities.domain.Result;
import edu.fjnu501.securities.domain.Stock;
import edu.fjnu501.securities.domain.StockHolder;
import edu.fjnu501.securities.service.CompanyService;
import edu.fjnu501.securities.service.StockService;
import edu.fjnu501.securities.state.ResultCodeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/get/all")
    @ResponseBody
    public Result getStockInfo() {
        List<Stock> allStockInfo = stockService.getAllStockInfo();
        if (allStockInfo != null) {
            return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", allStockInfo);
        }
        return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
    }

    @RequestMapping(value = "/get/{uid}")
    @ResponseBody
    public Result getStockSumByUid(@PathVariable("uid") int uid) {
        List<StockHolder> stockSumByUid = null;
        try {
            stockSumByUid = stockService.getStockSumByUid(uid);
        } catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), "获取失败", null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), "获取成功", stockSumByUid);
    }

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/company/addCompany",method = RequestMethod.POST)
    @ResponseBody
    public Result addCompany(@RequestBody Company company){
        try {
            companyService.addCompany(company);
        } catch (Exception e) {
            return new Result(500, "添加失败", null);
        }
        return new Result(200, "添加成功", null);
    }
    @RequestMapping(value = "/company/updateCompany/{companyId}/{amount}")
    @ResponseBody
    public Result updateCompany(@PathVariable(value = "companyId")int companyId,@PathVariable(value = "amount") double amount)
    {
        try{
            companyService.updateCompany(companyId,amount);
            System.out.println(companyId);
            System.out.println(amount);

        }
        catch (Exception e) {
            return new Result(500, "更新失败", null);
        }
        return new Result(200, "更新成功", null);
    }
    @ResponseBody
    @RequestMapping("/company/array/{currPage}/{pageSize}")

    public Result getCompanyByArray(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize){
        List<Company>companyList=null;

        try{
            companyList =companyService.queryCompanysByArray(currPage,pageSize);
        }
        catch (Exception e) {
            return new Result(500, "查询失败", null);
        }
        return new Result(200, "查询成功", companyList);

    }
    @ResponseBody
    @RequestMapping("/company/deleteCompany/{id}")
    public Result deleteCompany(@PathVariable("id") int id)
    {
        try{
            companyService.deleteCompany(id);
        }
        catch (Exception e) {
            return new Result(500, "删除失败", null);
        }
        return new Result(200, "删除成功", null);
    }

}
