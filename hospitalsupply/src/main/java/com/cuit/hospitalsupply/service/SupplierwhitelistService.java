package com.cuit.hospitalsupply.service;

import com.cuit.hospitalsupply.pojo.Supplierwhitelist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 15512
* @description 针对表【t_supplierwhitelist】的数据库操作Service
* @createDate 2024-02-18 16:52:09
*/
public interface SupplierwhitelistService extends IService<Supplierwhitelist> {

    boolean delete(Supplierwhitelist supplierwhitelist);
}
