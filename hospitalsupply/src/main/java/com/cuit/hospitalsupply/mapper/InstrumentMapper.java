package com.cuit.hospitalsupply.mapper;

import com.cuit.hospitalsupply.pojo.Instrument;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 15512
* @description 针对表【t_instrument】的数据库操作Mapper
* @createDate 2024-02-18 16:51:57
* @Entity com.cuit.hospitalsupply.pojo.Instrument
*/
public interface InstrumentMapper extends BaseMapper<Instrument> {

    void updateByInstrumentId(Instrument instrument);

    void deleteByInstrumentId(String instrumentid);
}




