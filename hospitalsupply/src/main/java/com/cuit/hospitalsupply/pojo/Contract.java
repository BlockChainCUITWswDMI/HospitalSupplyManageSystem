package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_contrast
 */
@TableName(value ="t_contract")
@Data
public class Contract implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String contractid;

    private String whitelistid;

    private String supplierid;

    private String hospitalid;

    private Integer deposit;

    private Integer dedit;

    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    private String udi;

    private Integer amount;

    private Integer orderid;

    private static final long serialVersionUID = 1L;
}