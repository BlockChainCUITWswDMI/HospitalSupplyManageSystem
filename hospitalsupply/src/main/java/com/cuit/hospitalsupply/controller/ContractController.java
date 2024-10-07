package com.cuit.hospitalsupply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Contract;
import com.cuit.hospitalsupply.service.ContractService;
import com.cuit.hospitalsupply.util.JwtHelper;
import com.cuit.hospitalsupply.util.Result;
import com.cuit.hospitalsupply.vo.response.ContractShowModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "合同控制器")
@RestController
@RequestMapping("/hs/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @ApiOperation("新增contrast")
    @PostMapping("/saveContract")
    public Result saveContrast(
            @ApiParam("JSON 的contrast对象") @RequestBody Contract contract
    ){
        boolean flag1 = contractService.verifyIfSupplierInWhitelist(contract);
        boolean flag2 = contractService.verifyIfInstrumentInSystem(contract);
        // 调用服务层完成修改
        if (flag1 != true){
            return Result.fail().message("供应商不在白名单中");
        }
        if (flag2 != true) {
            return Result.fail().message("设备不在系统中，请先添加设备");
        }
        contract.setCreateDate(new Date()); // 创建时间为当前时间；
        contract.setOrderid(0);  // 合同编号默认设置为0；
        boolean flag = contractService.save(contract);
        if (flag)
            return Result.ok().message("创建合同成功");
        else
            return Result.fail().message("创建合同失败");
    }

    @ApiOperation("上链，添加OrderId")
    @PostMapping("/addOrderId")
    public Result addOrderId(
            @ApiParam("JSON 的contrast对象") @RequestBody Contract contract
    ){
        boolean flag = contractService.updateById(contract);
        if (flag)
            return Result.ok().message("上链，添加OrderId");
        else
            return Result.fail().message("添加OrderId失败");
    }

    @ApiOperation("医院根据器械名称模糊查询，带分页")
    @GetMapping("/getContrastShowModel")
    public Result getContrasts(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "5") int pageSize,
            @ApiParam("分页查询模糊匹配的名称") String instrumentname,
            @ApiParam("token口令 ") @RequestHeader("token") String token
    ){
        // 分页 带条件查询
        Page<ContractShowModel> page = new Page<>(pageNo,pageSize);
        String hospitalId = JwtHelper.getUserId(token);
        // 通过服务层分页查询
        IPage<ContractShowModel> pageRs = contractService.getContractShowModelByOpr(page,hospitalId,instrumentname);
        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询合同成功");
    }

    @ApiOperation("供应商根据器械名称模糊查询，带分页")
    @GetMapping("/getSupplierContrastShowModel")
    public Result getSupplierContrastShowModel(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "5") int pageSize,
            @ApiParam("分页查询模糊匹配的名称") String instrumentname,
            @ApiParam("token口令 ") @RequestHeader("token") String token
    ){
        // 分页 带条件查询
        Page<ContractShowModel> page = new Page<>(pageNo,pageSize);
        String supplierId = JwtHelper.getUserId(token);
        // 通过服务层分页查询
        IPage<ContractShowModel> pageRs = contractService.getSupplierContractShowModelByOpr(page,supplierId,instrumentname);
        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询合同成功");
    }

}
