package com.achp.service;

import com.achp.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer size) throws Exception;
}
