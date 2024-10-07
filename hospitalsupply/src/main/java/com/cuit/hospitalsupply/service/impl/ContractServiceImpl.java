package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.mapper.ContractShowModelMapper;
import com.cuit.hospitalsupply.mapper.InstrumentMapper;
import com.cuit.hospitalsupply.mapper.SupplierwhitelistMapper;
import com.cuit.hospitalsupply.pojo.Contract;
import com.cuit.hospitalsupply.pojo.Instrument;
import com.cuit.hospitalsupply.pojo.Supplierwhitelist;
import com.cuit.hospitalsupply.service.ContractService;
import com.cuit.hospitalsupply.mapper.ContractMapper;
import com.cuit.hospitalsupply.vo.response.ContractShowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 15512
* @description 针对表【t_contrast】的数据库操作Service实现
* @createDate 2024-02-18 16:45:36
*/
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract>
    implements ContractService {

    @Autowired
    private SupplierwhitelistMapper supplierwhitelistMapper;

    @Autowired
    private ContractShowModelMapper contractShowModelMapper;

    @Autowired
    private InstrumentMapper instrumentMapper;

    // 医院 分页查询合同信息，返回Model
    @Override
    public IPage<ContractShowModel> getContractShowModelByOpr(Page<ContractShowModel> pageParam, String hospitalid , String instrumentname) {
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(instrumentname)){
            queryWrapper.like("i.instrumentname",instrumentname);
        }
        if (!StringUtils.isEmpty(hospitalid)){
            queryWrapper.eq("c.hospitalid",hospitalid);
        }
        queryWrapper.orderByDesc("contractid");

        Page<ContractShowModel> page = contractShowModelMapper.findContractPage(pageParam, queryWrapper);
        return page;
    }

    // 供应商 分页查询合同信息，返回Model
    @Override
    public IPage<ContractShowModel> getSupplierContractShowModelByOpr(Page<ContractShowModel> pageParam, String supplierId, String instrumentname) {
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(instrumentname)){
            queryWrapper.like("i.instrumentname",instrumentname);
        }
        if (!StringUtils.isEmpty(supplierId)){
            queryWrapper.eq("c.supplierid",supplierId);
        }
        queryWrapper.orderByDesc("contractid");

        Page<ContractShowModel> page = contractShowModelMapper.findContractPage(pageParam, queryWrapper);
        return page;
    }

    // 验证供应商是否在白名单中
    @Override
    public boolean verifyIfSupplierInWhitelist(Contract contract) {
        QueryWrapper<Supplierwhitelist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplierid", contract.getSupplierid())
                .eq("whitelistid", contract.getWhitelistid());
        Integer count = supplierwhitelistMapper.selectCount(queryWrapper);
        if (count != 0){
            return true;
        }
        return false;
    }

    // 验证设备信息是否在系统中
    @Override
    public boolean verifyIfInstrumentInSystem(Contract contract) {
        QueryWrapper<Instrument> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplierid", contract.getSupplierid())
                .eq("udi", contract.getUdi());
        Integer count = instrumentMapper.selectCount(queryWrapper);
        if (count != null) {
            return true;
        }
        return false;
    }

}
