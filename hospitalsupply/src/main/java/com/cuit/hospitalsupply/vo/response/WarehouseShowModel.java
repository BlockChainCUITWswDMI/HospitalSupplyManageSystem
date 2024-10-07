package com.cuit.hospitalsupply.vo.response;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WarehouseShowModel {
    private String warehouseid;

    private String hospitalid;

    private String hospitalname;

    private String suppliername;

    private Integer amount;

    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date addDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    private String status;

    private String instrumentid;

    private String instrumentname;
}
