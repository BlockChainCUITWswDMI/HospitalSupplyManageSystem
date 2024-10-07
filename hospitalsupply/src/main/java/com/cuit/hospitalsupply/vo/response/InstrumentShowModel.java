package com.cuit.hospitalsupply.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class InstrumentShowModel {

    private String instrumentid;

    private String supplierid;

    private String suppliername;

    private String instrumentname;

    private String udi;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productionTime;

}
