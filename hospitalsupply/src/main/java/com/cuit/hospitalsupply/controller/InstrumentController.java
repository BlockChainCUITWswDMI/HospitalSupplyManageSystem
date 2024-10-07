package com.cuit.hospitalsupply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Instrument;
import com.cuit.hospitalsupply.service.InstrumentService;
import com.cuit.hospitalsupply.util.Result;
import com.cuit.hospitalsupply.vo.response.InstrumentShowModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "医疗器械控制器")
@RestController
@RequestMapping("/hs/instrument")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @ApiOperation("删除Instrument信息")
    @PostMapping("/deleteInstrument")
    public Result deleteInstrument(
            @ApiParam("要删除的instrument的ID") @RequestBody Instrument instrument
    ){
        String instrumentId = instrument.getInstrumentid();
        boolean flag = instrumentService.removeByInstrumentId(instrumentId);
        if (flag)
            return Result.ok().message("设备删除成功");
        else
            return Result.fail().message("设备删除失败");
    }

    @ApiOperation("新增instrument")
    @PostMapping("/saveInstrument")
    public Result saveInstrument(
            @ApiParam("JSON 的instrument对象") @RequestBody Instrument instrument
    ){
        // 调用服务层完成修改
        boolean flag = instrumentService.save(instrument);
        if (flag)
            return Result.ok().message("新建设备成功");
        else
            return Result.fail().message("新建设备失败");
    }

    @ApiOperation("修改instrument")
    @PostMapping("/updateInstrument")
    public Result updateInstrument( @ApiParam("JSON 的instrument对象") @RequestBody Instrument instrument
    ){
        // 调用服务层完成修改
        boolean flag = instrumentService.updateByInstrumentId(instrument);
        if (flag)
            return Result.ok().message("修改设备成功");
        else
            return Result.fail().message("修改设备失败");
    }

    @ApiOperation("根据instrumentId查询instrument")
    @PostMapping("/selectInstrument")
    public Result selectOneInstrument( @ApiParam("String类型 instrumentId") @RequestBody String instrumentId
    ){
        // 调用服务层完成修改
        Instrument instrument = instrumentService.getByInstrumentId(instrumentId);
        // 新建白名单
        return Result.ok(instrument).message("根据instrumentID查询器械信息成功");
    }

    @ApiOperation("根据器械名称模糊查询，带分页")
    @GetMapping("/getInstrument")
    public Result getInstruments(
            @ApiParam("分页查询页码数") @RequestParam(defaultValue = "1") int pageNo,
            @ApiParam("分页查询的页大小") @RequestParam(defaultValue = "10") int pageSize,
            @ApiParam("分页查询模糊匹配的名称") @RequestParam(defaultValue = "")String instrumentname
    ){
        // 分页 带条件查询
        Page<InstrumentShowModel> page = new Page<>(pageNo,pageSize);

        // 通过服务层
        IPage<InstrumentShowModel> pageRs = instrumentService.getInstrumentByOpr(page,instrumentname);

        // 封装result对象返回
        return Result.ok(pageRs).message("分页查询器械设备成功");
    }
}
