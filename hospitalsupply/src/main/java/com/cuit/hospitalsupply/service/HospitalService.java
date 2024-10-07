package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.hospitalsupply.pojo.LoginForm;

/**
* @author 15512
* @description 针对表【t_hospital】的数据库操作Service
* @createDate 2024-02-18 16:51:48
*/
public interface HospitalService extends IService<Hospital> {

    Hospital login(LoginForm loginForm);

    Hospital getByHospitalId(String userId);

    boolean updateByHospitalId(Hospital hospital);

    IPage<Hospital> getHospitalByHospitalId(Page<Hospital> page, String hospitalId);

    IPage<Hospital> getHospitalByOpr(Page<Hospital> page, String hospitalname);
}
