package com.cuit.hospitalsupply.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Contract;
import com.cuit.hospitalsupply.vo.response.ContractShowModel;
import org.apache.ibatis.annotations.Param;

/**
* @author 15512
* @description 针对表【t_contrast】的数据库操作Mapper
* @createDate 2024-02-18 16:45:36
* @Entity com.cuit.hospitalsupply.pojo.Contrast
*/
public interface ContractShowModelMapper extends BaseMapper<ContractShowModel> {
    Page<ContractShowModel> findContractPage(Page<ContractShowModel> pageParam, @Param("ew") QueryWrapper<Contract> queryWrapper);
}




