package com.cuit.hospitalsupply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Hospital;
import com.cuit.hospitalsupply.pojo.Whitelist;
import com.cuit.hospitalsupply.service.HospitalService;
import com.cuit.hospitalsupply.service.WhitelistService;
import com.cuit.hospitalsupply.util.JwtHelper;
import com.cuit.hospitalsupply.util.MD5;
import com.cuit.hospitalsupply.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "医院信息控制器")
@RestController
@RequestMapping("/hs/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private WhitelistService whitelistService;

    @ApiOperation("新增hospital")
    @PostMapping("/saveHospital")
    public Result saveHospital(
            @ApiParam("JSON 的instrument对象") @RequestBody Hospital hospital
    ){
        // 新建白名单
        Whitelist whitelist = new Whitelist();
        creatWhiteList(hospital,whitelist);
        whitelistService.save(whitelist);
        // 调用服务层完成修改
        hospital.setPassword(MD5.encrypt(hospital.getPassword()));
        boolean flag = hospitalService.save(hospital);
        if (flag)
            return Result.ok().message("新建医院信息成功");
        else
            return Result.fail().message("新建医院信息失败");
    }

    @ApiOperation("修改hospital")
    @PostMapping("/updateHospital")
    public Result updateInstrument(
            @ApiParam("JSON 的hospital对象") @RequestBody Hospital hospital
    ){
        // 调用服务层完成修改
        boolean flag = hospitalService.updateByHospitalId(hospital);
        if (flag)
            return Result.ok().message("修改医院信息成功");
        else
            return Result.fail().message("修改医院信息失败");
    }

    @ApiOperation("根据hospitalId查询hospital")
    @GetMapping("/selectHospital")
    public Result selectOneHospital(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "1") int pageSize,
            @ApiParam("token口令 ") @RequestHeader("token") String token
            
    ){
        Page<Hospital> page = new Page<>(pageNo,pageSize);
        String hospitalId = JwtHelper.getUserId(token);
        // 通过服务层
        IPage<Hospital> pageRs = hospitalService.getHospitalByHospitalId(page,hospitalId);
        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询医院成功");
    }

    @ApiOperation("根据hospitalId查询hospital")
    @GetMapping("/selectAllHospital")
    public Result selectHospital(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "1") int pageSize,
            @ApiParam("分页查询模糊匹配的名称")@RequestParam(defaultValue = "")  String hospitalname
    ){
        Page<Hospital> page = new Page<>(pageNo,pageSize);
        // 通过服务层
        IPage<Hospital> pageRs = hospitalService.getHospitalByOpr(page,hospitalname);
        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询医院成功");
    }

    // 内部方法，创造白名单
    private void creatWhiteList(Hospital hospital, Whitelist whitelist){
        whitelist.setWhitelistid(hospital.getWhitelistid());
        whitelist.setHospitalid(hospital.getHospitalid());
    }
}
