package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_instrument
 */
@TableName(value ="t_instrument")
@Data
public class Instrument implements Serializable {
    private String instrumentid;

    private String supplierid;

    private String instrumentname;

    private String udi;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productionTime;

    private static final long serialVersionUID = 1L;
}