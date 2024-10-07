package com.cuit.hospitalsupply.mapper;

import com.cuit.hospitalsupply.pojo.Supplierwhitelist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 15512
* @description 针对表【t_supplierwhitelist】的数据库操作Mapper
* @createDate 2024-02-18 16:52:09
* @Entity com.cuit.hospitalsupply.pojo.Supplierwhitelist
*/
public interface SupplierwhitelistMapper extends BaseMapper<Supplierwhitelist> {

    boolean remove(Supplierwhitelist supplierwhitelist);
}




