package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.pojo.Whitelist;
import com.cuit.hospitalsupply.service.WhitelistService;
import com.cuit.hospitalsupply.mapper.WhitelistMapper;
import org.springframework.stereotype.Service;

/**
* @author 15512
* @description 针对表【t_whitelist】的数据库操作Service实现
* @createDate 2024-02-18 16:52:15
*/
@Service
public class WhitelistServiceImpl extends ServiceImpl<WhitelistMapper, Whitelist>
    implements WhitelistService{
}




