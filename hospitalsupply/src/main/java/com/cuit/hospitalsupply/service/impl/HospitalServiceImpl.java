package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.pojo.Hospital;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.service.HospitalService;
import com.cuit.hospitalsupply.mapper.HospitalMapper;
import com.cuit.hospitalsupply.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 15512
* @description 针对表【t_hospital】的数据库操作Service实现
* @createDate 2024-02-18 16:51:48
*/
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital>
    implements HospitalService{

    @Autowired
    private HospitalMapper hospitalMapper;

    // 登录
    @Override
    public Hospital login(LoginForm loginForm) {
        QueryWrapper<Hospital> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hospitalname",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Hospital hospital = hospitalMapper.selectOne(queryWrapper);
        return hospital;
    }

    // 通过医院ID获取医院信息
    @Override
    public Hospital getByHospitalId(String hospitalId) {
        QueryWrapper<Hospital> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hospitalId",hospitalId);
        return hospitalMapper.selectOne(queryWrapper);
    }

    // 通过医院ID更新医院信息
    @Override
    public boolean updateByHospitalId(Hospital hospital) {
        hospital.setPassword(MD5.encrypt(hospital.getPassword()));
        hospitalMapper.updateByHospitalId(hospital);
        return true;
    }

    // 分页查询医院信息
    @Override
    public IPage<Hospital> getHospitalByHospitalId(Page<Hospital> pageParam, String hospitalId) {
        QueryWrapper<Hospital> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(hospitalId)){
            queryWrapper.eq("hospitalid",hospitalId);
        }

        Page<Hospital> page = hospitalMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public IPage<Hospital> getHospitalByOpr(Page<Hospital> pageParam, String hospitalname) {
        QueryWrapper<Hospital> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(hospitalname)){
            queryWrapper.eq("hospitalname",hospitalname);
        }

        Page<Hospital> page = hospitalMapper.selectPage(pageParam, queryWrapper);
        return page;
    }
}