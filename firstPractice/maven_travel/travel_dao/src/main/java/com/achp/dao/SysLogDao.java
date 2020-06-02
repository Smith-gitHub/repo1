package com.achp.dao;

import com.achp.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) VALUES (#{log.visitTime},#{log.username},#{log.ip},#{log.url},#{log.executionTime},#{log.method})")
    void save(@Param("log") SysLog sysLog) throws Exception;

    @Select("select * from syslog order by visitTime desc")
    List<SysLog> findAll();
}
