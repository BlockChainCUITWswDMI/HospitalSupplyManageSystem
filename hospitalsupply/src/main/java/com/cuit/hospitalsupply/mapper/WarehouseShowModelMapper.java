package com.cuit.hospitalsupply.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.vo.response.WarehouseShowModel;
import org.apache.ibatis.annotations.Param;

public interface WarehouseShowModelMapper extends BaseMapper<WarehouseShowModel> {
    Page<WarehouseShowModel> findWarehousePage(Page<WarehouseShowModel> pageParam,  @Param("ew") QueryWrapper<WarehouseShowModel> queryWrapper);
}
