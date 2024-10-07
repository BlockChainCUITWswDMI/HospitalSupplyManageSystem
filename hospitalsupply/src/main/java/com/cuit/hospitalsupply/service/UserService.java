package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.hospitalsupply.pojo.LoginForm;
import com.cuit.hospitalsupply.pojo.User;

/**
* @author 15512
* @description 针对表【t_instrument】的数据库操作Service
* @createDate 2024-02-18 16:51:57
*/
public interface UserService extends IService<User> {

    User login(LoginForm loginForm);
}
