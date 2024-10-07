package com.cuit.hospitalsupply.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
* @author 15512
* @description 针对表【t_supplier】的数据库操作Mapper
* @createDate 2024-02-18 16:52:06
* @Entity com.cuit.hospitalsupply.pojo.Supplier
*/
public interface SupplierMapper extends BaseMapper<Supplier> {

    void updateBySupplierId(Supplier supplier);

    Page<Supplier> findSupplierPage(Page<Supplier> pageParam, @Param(Constants.WRAPPER) QueryWrapper<Supplier> queryWrapper);

    void create(Supplier supplier);
}




