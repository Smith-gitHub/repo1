package com.achp.dao;


import com.achp.domain.Role;
import com.achp.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {


    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.achp.dao.RolesDao.findById"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(REPLACE(UUID(),\"-\",\"\"),#{user.email},#{user.username},#{user.password},#{user.phoneNum},#{user.status})")
    void save(@Param("user") UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{userId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.achp.dao.RolesDao.findById"))
    })
    UserInfo findById(String userId);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    List<Role> findRoles(String userId) throws Exception;


    @Delete("delete from users_role where userId=#{userId} and roleId=#{roleId}")
    void delRole(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;

    @Update("update users set username = #{user.username},password = #{user.password},email = #{user.email},phoneNum = #{user.phoneNum},status=#{user.status} where id = #{user.id}")
    void update(@Param("user") UserInfo user) throws Exception;
}
