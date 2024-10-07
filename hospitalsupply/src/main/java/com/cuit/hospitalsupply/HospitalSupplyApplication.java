package com.cuit.hospitalsupply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/cuit/hospitalsupply/mapper")
public class HospitalSupplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalSupplyApplication.class, args);
    }

}