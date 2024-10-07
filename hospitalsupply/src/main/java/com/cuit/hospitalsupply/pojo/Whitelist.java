package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_whitelist
 */
@TableName(value ="t_whitelist")
@Data
public class Whitelist implements Serializable {
    private String whitelistid;

    private String hospitalid;

    private static final long serialVersionUID = 1L;
}