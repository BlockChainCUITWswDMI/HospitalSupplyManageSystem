package com.cuit.hospitalsupply.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.vo.response.InstrumentShowModel;
import org.apache.ibatis.annotations.Param;

/**
* @author 15512
* @description 针对表【t_instrument】的数据库操作Mapper
* @createDate 2024-02-18 16:51:57
* @Entity com.cuit.hospitalsupply.pojo.Instrument
*/
public interface InstrumentShowModelMapper extends BaseMapper<InstrumentShowModel> {

    Page<InstrumentShowModel> findWarehousePage(
            Page<InstrumentShowModel> pageParam, @Param("ew") QueryWrapper<InstrumentShowModel> ew);
}




