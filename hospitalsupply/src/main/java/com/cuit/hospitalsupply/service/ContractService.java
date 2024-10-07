package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Contract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.hospitalsupply.vo.response.ContractShowModel;

/**
* @author 15512
* @description 针对表【t_contrast】的数据库操作Service
* @createDate 2024-02-18 16:45:36
*/
public interface ContractService extends IService<Contract> {

    boolean verifyIfSupplierInWhitelist(Contract contract);

    IPage<ContractShowModel> getContractShowModelByOpr(Page<ContractShowModel> page, String hospitalId, String udi);

    boolean verifyIfInstrumentInSystem(Contract contract);

    IPage<ContractShowModel> getSupplierContractShowModelByOpr(Page<ContractShowModel> page, String supplierId, String instrumentname);
}
