package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_hospital
 */
@TableName(value ="t_hospital")
@Data
public class Hospital implements Serializable {
    private String hospitalid;

    private String whitelistid;

    private String hospitalname;

    private String hosUsci;

    private String password;

    private static final long serialVersionUID = 1L;
}