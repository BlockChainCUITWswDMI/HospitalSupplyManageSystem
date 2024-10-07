package com.cuit.hospitalsupply.mapper;

import com.cuit.hospitalsupply.pojo.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 15512
* @description 针对表【t_warehouse】的数据库操作Mapper
* @createDate 2024-02-18 16:52:12
* @Entity com.cuit.hospitalsupply.pojo.Warehouse
*/
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    void updateByWarehouseId(Warehouse warehouse);

    void deleteByWarehouseId(String warehouseid);
}




