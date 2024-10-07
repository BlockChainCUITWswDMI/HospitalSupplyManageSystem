package com.cuit.hospitalsupply.vo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ContractShowModel {

    private Integer id;

    private String contractid;

    private String whitelistid;

    private String supplierid;

    private String suppliername;

    private String hospitalid;

    private String hospitalname;

    private Integer deposit;

    private Integer dedit;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    private String udi;

    private String instrumentname;

    private Integer amount;

    private Integer orderid;
}
