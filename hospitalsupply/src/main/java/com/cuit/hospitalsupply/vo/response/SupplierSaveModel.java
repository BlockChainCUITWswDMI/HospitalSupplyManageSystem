package com.cuit.hospitalsupply.vo.response;

import lombok.Data;


@Data
public class SupplierSaveModel {
    private String supplierid;

    private String suppliername;

    private String supusci;

    private String password;

    private String whitelistid;
}
