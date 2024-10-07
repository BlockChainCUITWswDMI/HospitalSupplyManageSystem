package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.mapper.WarehouseShowModelMapper;
import com.cuit.hospitalsupply.pojo.Warehouse;
import com.cuit.hospitalsupply.service.WarehouseService;
import com.cuit.hospitalsupply.mapper.WarehouseMapper;
import com.cuit.hospitalsupply.vo.response.WarehouseShowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
* @author 15512
* @description 针对表【t_warehouse】的数据库操作Service实现
* @createDate 2024-02-18 16:52:12
*/
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
    implements WarehouseService{

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseShowModelMapper warehouseShowModelMapper;

    // 修改库存信息
    @Override
    public boolean updateByWarehouseId(Warehouse warehouse) {
        warehouse.setUpdateDate(new Date());
        warehouseMapper.updateByWarehouseId(warehouse);
        return true;
    }

    // 分页查询库存信息
    @Override
    public IPage<WarehouseShowModel> getWarehouseShowModelByOpr(Page<WarehouseShowModel> pageParam, String hospitalId, String instrumentName) {
        QueryWrapper<WarehouseShowModel> ew = new QueryWrapper<>();
        if (!StringUtils.isEmpty(hospitalId)){
            ew.eq("w.hospitalid",hospitalId);
        }
        if (!StringUtils.isEmpty(instrumentName)){
            ew.like("i.instrumentname",instrumentName);
        }
        Page<WarehouseShowModel> page = warehouseShowModelMapper.findWarehousePage(pageParam, ew);

        return page;
    }

    // 查询库存信息，通过设备ID
    @Override
    public WarehouseShowModel getByInstrumentId(String instrumentId) {
        QueryWrapper<WarehouseShowModel> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(instrumentId)){
            queryWrapper.eq("instrumentId",instrumentId);
        }

        WarehouseShowModel one = warehouseShowModelMapper.selectOne(queryWrapper);
        return one;
    }

    // 删除库存信息
    @Override
    public boolean removeByWarehouseId(String warehouseId) {
        warehouseMapper.deleteByWarehouseId(warehouseId);
        return true;
    }
}
