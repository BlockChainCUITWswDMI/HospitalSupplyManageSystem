package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_supplierwhitelist
 */
@TableName(value ="t_supplierwhitelist")
@Data
public class Supplierwhitelist implements Serializable {
    private String supplierid;

    private String whitelistid;

    private static final long serialVersionUID = 1L;
}