package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.pojo.Supplier;
import com.cuit.hospitalsupply.service.SupplierService;
import com.cuit.hospitalsupply.mapper.SupplierMapper;
import com.cuit.hospitalsupply.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 15512
* @description 针对表【t_supplier】的数据库操作Service实现
* @createDate 2024-02-18 16:52:06
*/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
    implements SupplierService{

    @Autowired
    private SupplierMapper supplierMapper;

    // 登录
    @Override
    public Supplier login(LoginForm loginForm) {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("suppliername",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Supplier supplier = supplierMapper.selectOne(queryWrapper);
        return supplier;
    }

    // 根据供应商ID查询供应商信息
    @Override
    public Supplier getBySupplierId(String supplierId) {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplierId",supplierId);
        return supplierMapper.selectOne(queryWrapper);
    }

    // 更新供应商信息
    @Override
    public boolean updateBySupplierId(Supplier supplier) {
        supplierMapper.updateBySupplierId(supplier);
        return true;
    }

    // 分页查询供应商信息
    @Override
    public IPage<Supplier> getSupplierByOpr(Page<Supplier> pageParam, String hospitalId, String supplierName) {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(supplierName)){
            queryWrapper.like("suppliername",supplierName);
        }
        if (!StringUtils.isEmpty(hospitalId)){
            queryWrapper.eq("h.hospitalid",hospitalId);
        }
        queryWrapper.orderByDesc("supplierid");

        Page<Supplier> page = supplierMapper.findSupplierPage(pageParam, queryWrapper);
        return page;
    }

    // 新建供应商
    @Override
    public void create(Supplier supplier) {
        supplierMapper.create(supplier);
    }
}
