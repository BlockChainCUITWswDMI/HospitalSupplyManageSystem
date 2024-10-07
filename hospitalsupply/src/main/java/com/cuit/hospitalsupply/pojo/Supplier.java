package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_supplier
 */
@TableName(value ="t_supplier")
@Data
public class Supplier implements Serializable {
    private String supplierid;

    private String suppliername;

    private String supusci;

    private String password;

    private static final long serialVersionUID = 1L;
}