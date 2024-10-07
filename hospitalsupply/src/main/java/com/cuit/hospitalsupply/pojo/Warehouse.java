package com.cuit.hospitalsupply.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_warehouse
 */
@TableName(value ="t_warehouse")
@Data
public class Warehouse implements Serializable {
    private String warehouseid;

    private String hospitalid;

    private Integer amount;

    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date addDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    private String status;

    private String instrumentid;

    private static final long serialVersionUID = 1L;
}