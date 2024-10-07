package com.cuit.hospitalsupply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.hospitalsupply.mapper.InstrumentShowModelMapper;
import com.cuit.hospitalsupply.pojo.Instrument;
import com.cuit.hospitalsupply.service.InstrumentService;
import com.cuit.hospitalsupply.mapper.InstrumentMapper;
import com.cuit.hospitalsupply.vo.response.InstrumentShowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 15512
* @description 针对表【t_instrument】的数据库操作Service实现
* @createDate 2024-02-18 16:51:57
*/
@Service
public class InstrumentServiceImpl extends ServiceImpl<InstrumentMapper, Instrument>
    implements InstrumentService{

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Autowired
    private InstrumentShowModelMapper instrumentShowModelMapper;

    //  分页查询 医疗器械信息
    @Override
    public IPage<InstrumentShowModel> getInstrumentByOpr(Page<InstrumentShowModel> pageParam, String instrumentName) {
        QueryWrapper<InstrumentShowModel> ew = new QueryWrapper<>();

        if (!StringUtils.isEmpty(instrumentName)){
            ew.like("instrumentname",instrumentName);
        }
        ew.orderByDesc("instrumentid");

        Page<InstrumentShowModel> page = instrumentShowModelMapper.findWarehousePage(pageParam, ew);
        return page;
    }

    //  更新设备信息
    @Override
    public boolean updateByInstrumentId(Instrument instrument) {
        instrumentMapper.updateByInstrumentId(instrument);
        return true;
    }

    //  删除设备信息
    @Override
    public boolean removeByInstrumentId(String instrumentId) {
        instrumentMapper.deleteByInstrumentId(instrumentId);
        return true;
    }

    //  查询
    @Override
    public Instrument getByInstrumentId(String instrumentId) {
        QueryWrapper<Instrument> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("instrumentId",instrumentId);
        return instrumentMapper.selectOne(queryWrapper);
    }
}