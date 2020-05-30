package com.achp.dao;

import com.achp.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(id,permissionName,url) values(REPLACE(UUID(),\"-\",\"\"),#{p.permissionName},#{p.url})")
    void save(@Param("p") Permission permission) throws Exception;
}
