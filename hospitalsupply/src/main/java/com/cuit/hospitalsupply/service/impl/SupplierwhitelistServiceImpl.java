package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.pojo.Supplierwhitelist;
import com.cuit.hospitalsupply.service.SupplierwhitelistService;
import com.cuit.hospitalsupply.mapper.SupplierwhitelistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 15512
* @description 针对表【t_supplierwhitelist】的数据库操作Service实现
* @createDate 2024-02-18 16:52:09
*/
@Service
public class SupplierwhitelistServiceImpl extends ServiceImpl<SupplierwhitelistMapper, Supplierwhitelist>
    implements SupplierwhitelistService{

    @Autowired
    private SupplierwhitelistMapper supplierwhitelistMapper;

    @Override
    public boolean delete(Supplierwhitelist supplierwhitelist) {
        supplierwhitelistMapper.remove(supplierwhitelist);
        return false;
    }
}
