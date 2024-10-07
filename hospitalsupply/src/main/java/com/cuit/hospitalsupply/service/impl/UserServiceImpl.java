package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.mapper.UserMapper;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.pojo.User;
import com.cuit.hospitalsupply.service.UserService;
import com.cuit.hospitalsupply.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author 15512
* @description 针对表【t_supplier】的数据库操作Service实现
* @createDate 2024-02-18 16:52:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 登录
    @Override
    public User login(LoginForm loginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        User admin = userMapper.selectOne(queryWrapper);
        return admin;
    }

}
