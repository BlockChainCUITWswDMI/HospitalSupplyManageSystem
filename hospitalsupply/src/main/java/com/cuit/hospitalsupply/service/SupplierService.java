package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 15512
* @description 针对表【t_supplier】的数据库操作Service
* @createDate 2024-02-18 16:52:06
*/
public interface SupplierService extends IService<Supplier> {

    Supplier login(LoginForm loginForm);

    Supplier getBySupplierId(String userId);

    boolean updateBySupplierId(Supplier supplier);

    IPage<Supplier> getSupplierByOpr(Page<Supplier> page, String hospitalId, String supplierName);

    void create(Supplier supplier);
}
