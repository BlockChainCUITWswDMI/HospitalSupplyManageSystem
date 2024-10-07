package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.hospitalsupply.vo.response.WarehouseShowModel;

/**
* @author 15512
* @description 针对表【t_warehouse】的数据库操作Service
* @createDate 2024-02-18 16:52:12
*/
public interface WarehouseService extends IService<Warehouse> {

    boolean updateByWarehouseId(Warehouse warehouse);

    IPage<WarehouseShowModel> getWarehouseShowModelByOpr(Page<WarehouseShowModel> page, String hospitalId, String instrumentName);

    WarehouseShowModel getByInstrumentId(String instrumentId);

    boolean removeByWarehouseId(String warehouseId);
}
