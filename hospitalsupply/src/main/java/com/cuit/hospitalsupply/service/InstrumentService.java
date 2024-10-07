package com.cuit.hospitalsupply.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.hospitalsupply.pojo.Instrument;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.hospitalsupply.vo.response.InstrumentShowModel;

/**
* @author 15512
* @description 针对表【t_instrument】的数据库操作Service
* @createDate 2024-02-18 16:51:57
*/
public interface InstrumentService extends IService<Instrument> {

    IPage<InstrumentShowModel> getInstrumentByOpr(Page<InstrumentShowModel> page, String instrumentName);

    boolean updateByInstrumentId(Instrument instrument);

    boolean removeByInstrumentId(String instrumentId);

    Instrument getByInstrumentId(String instrumentId);
}
