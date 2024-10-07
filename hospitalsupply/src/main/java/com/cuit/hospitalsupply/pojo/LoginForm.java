package com.cuit.hospitalsupply.pojo;

import lombok.Data;

// 登录时的数据

@Data
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer usertype;

}
