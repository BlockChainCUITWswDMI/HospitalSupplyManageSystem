package com.cuit.hospitalsupply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Warehouse;
import com.cuit.hospitalsupply.service.WarehouseService;
import com.cuit.hospitalsupply.util.JwtHelper;
import com.cuit.hospitalsupply.util.Result;
import com.cuit.hospitalsupply.vo.response.WarehouseShowModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Api(tags = "库存控制器")
@RestController
@RequestMapping("/hs/warehouse")
public class WareHouseController {

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation("删除Warehouse信息")
    @PostMapping("/deleteWarehouse")
    public Result deleteWarehouse(
            @ApiParam("要删除的warehouse") @RequestBody Warehouse warehouse
    ){
        String warehouseid = warehouse.getWarehouseid();
        warehouseService.removeByWarehouseId(warehouseid);
        return Result.ok().message("成功删除库存信息");
    }

    @ApiOperation("新增warehouse")
    @PostMapping("/saveWarehouse")
    public Result saveWarehouse(
            @ApiParam("JSON 的warehouse对象") @RequestBody Warehouse warehouse
    ){
        // 调用服务层完成修改
        warehouse.setAddDate(new Date());
        warehouse.setUpdateDate(new Date());
        warehouseService.save(warehouse);
        return Result.ok().message("成功新增库存信息");
    }

    @ApiOperation("修改warehouse")
    @PostMapping("/updateWarehouse")
    public Result updateWarehouse(
            @ApiParam("JSON 的warehouse对象") @RequestBody Warehouse warehouse
    ){
        // 调用服务层完成修改
        warehouseService.updateByWarehouseId(warehouse);
        return Result.ok().message("成功修改库存信息");
    }

    @ApiOperation("根据instrumentId查询warehouse")
    @PostMapping("/selectWarehouseShowModel")
    public Result selectOneWarehouseShowModel(
            @ApiParam("String类型 instrumentId") @RequestBody String instrumentId
    ){
        // 调用服务层完成修改
        WarehouseShowModel warehouse = warehouseService.getByInstrumentId(instrumentId);
        // 新建白名单
        return Result.ok(warehouse).message("查询单一器械信息成功");
    }

    @ApiOperation("根据器械名称模糊查询，带分页")
    @GetMapping("/getWarehouses")
    public Result getWarehouseShowModels(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "5") int pageSize,
            @ApiParam("分页查询模糊匹配的名称") @RequestParam(defaultValue = "") String instrumentname,
            @ApiParam("token口令 ") @RequestHeader("token") String token
    ){
        // 分页 带条件查询
        Page<WarehouseShowModel> page = new Page<>(pageNo,pageSize);
        String hospitalId = JwtHelper.getUserId(token);
        // 通过服务层
        IPage<WarehouseShowModel> pageRs =warehouseService.getWarehouseShowModelByOpr(page,hospitalId,instrumentname);
        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询库存信息");
    }
}
