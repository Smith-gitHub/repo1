package com.achp.service.impl;

import com.achp.dao.SysLogDao;
import com.achp.domain.SysLog;
import com.achp.service.SysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {


        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
