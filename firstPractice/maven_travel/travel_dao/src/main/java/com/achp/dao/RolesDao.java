package com.achp.dao;

import com.achp.domain.Permission;
import com.achp.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RolesDao {


    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.achp.dao.PermissionDao.findByRoleId")),
    })
    List<Role> findById(String userId) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws Exception;


    @Insert("insert into role(id,roleName,roleDesc) values(REPLACE(UUID(),\"-\",\"\"),#{role.roleName},#{role.roleDesc})")
    void save(@Param("role") Role role) throws Exception;

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.achp.dao.PermissionDao.findByRoleId")),
    })
    Role findByRoleId(String id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermissions(String id) throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values(#{id},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("id") String id) throws Exception;
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissions(String id);

    @Delete("delete from role_permission where permissionId = #{id} and roleId = #{roleId}")
    void delPermission(@Param("roleId") String roleId, @Param("id") String id);
}
