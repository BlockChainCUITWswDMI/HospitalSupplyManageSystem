package com.cuit.hospitalsupply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Supplier;
import com.cuit.hospitalsupply.pojo.Supplierwhitelist;
import com.cuit.hospitalsupply.service.SupplierService;
import com.cuit.hospitalsupply.service.SupplierwhitelistService;
import com.cuit.hospitalsupply.util.JwtHelper;
import com.cuit.hospitalsupply.util.MD5;
import com.cuit.hospitalsupply.util.Result;
import com.cuit.hospitalsupply.vo.response.SupplierSaveModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "供应商信息控制器")
@RestController
@RequestMapping("/hs/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierwhitelistService supplierwhitelistService;

    @ApiOperation("新增supplier")
    @PostMapping("/saveSupplier")
    public Result saveSupplier(
            @ApiParam("JSON 的instrument对象") @RequestBody SupplierSaveModel supplierSaveModel
    ){
        // 调用服务层完成修改，新建供应商
        Supplier supplier = new Supplier();
        creatSupplier(supplierSaveModel,supplier);
        supplierService.create(supplier);
        // 新建供应商-白名单
        Supplierwhitelist supplierwhitelist = new Supplierwhitelist();
        creatSupplierWhitelist(supplierSaveModel,supplierwhitelist);
        supplierwhitelistService.save(supplierwhitelist);
        return Result.ok().message("新建供应商成功");
    }

    @ApiOperation("将供应商移出白名单")
    @PostMapping("/deleteSupplierfromwhitelist")
    public Result deleteSupplierfromwhitelist(
            @ApiParam("JSON 的instrument对象") @RequestBody SupplierSaveModel supplierSaveModel
    ){
        // 供应商-白名单
        Supplierwhitelist supplierwhitelist = new Supplierwhitelist();
        supplierwhitelist.setWhitelistid(supplierSaveModel.getWhitelistid());
        supplierwhitelist.setSupplierid(supplierSaveModel.getSupplierid());
        supplierwhitelistService.delete(supplierwhitelist);
        return Result.ok().message("将供应商移除白名单成功");
    }

    @ApiOperation("修改supplier")
    @PostMapping("/updateSupplier")
    public Result updateSupplier(
            @ApiParam("JSON 的instrument对象") @RequestBody SupplierSaveModel supplierSaveModel
    ){
        // 调用服务层完成修改
        Supplier supplier = new Supplier();
        creatSupplier(supplierSaveModel,supplier);
        boolean flag = supplierService.updateBySupplierId(supplier);
        if (flag)
            return Result.ok().message("修改供应商成功");
        else
            return Result.fail().message("修改供应商失败");
    }

    @ApiOperation("根据器械名称模糊查询，带分页")
    @GetMapping("/getSuppliers")
    public Result getSuppliers(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "5") int pageSize,
            @ApiParam("分页查询模糊匹配的名称") @RequestParam(defaultValue = "") String suppliername,
            @ApiParam("token口令 ") @RequestHeader("token") String token
    ){
        // 分页 带条件查询
        Page<Supplier> page = new Page<>(pageNo,pageSize);
        String hospitalId = JwtHelper.getUserId(token);
        // 通过服务层
        IPage<Supplier> pageRs = supplierService.getSupplierByOpr(page,hospitalId,suppliername);

        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询供应商成功");

    }

    // 内部方法，输入model，设置supplier属性
    private void creatSupplier(SupplierSaveModel supplierSaveModel, Supplier supplier){
        supplier.setSupplierid(supplierSaveModel.getSupplierid());
        supplier.setSuppliername(supplierSaveModel.getSuppliername());
        supplier.setSupusci(supplierSaveModel.getSupusci());
        supplier.setPassword(MD5.encrypt(supplierSaveModel.getPassword()));
    }

    // 内部方法，输入model，设置Whitelist属性
    private void creatSupplierWhitelist(SupplierSaveModel supplierSaveModel, Supplierwhitelist supplierwhitelist){
        supplierwhitelist.setSupplierid(supplierSaveModel.getSupplierid());
        supplierwhitelist.setWhitelistid(supplierSaveModel.getWhitelistid());
    }
}
