package com.cuit.hospitalsupply.mapper;

import com.cuit.hospitalsupply.pojo.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 15512
* @description 针对表【t_hospital】的数据库操作Mapper
* @createDate 2024-02-18 16:51:48
* @Entity com.cuit.hospitalsupply.pojo.Hospital
*/
public interface HospitalMapper extends BaseMapper<Hospital> {

    void updateByHospitalId(Hospital hospital);
}




