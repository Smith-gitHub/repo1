package com.achp.dao;

import com.achp.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 增
     * @param user
     */
    @Insert("insert into user (username,password) values(#{user.username},#{user.password})")
    void saveUser(@Param("user") User user);

    /**
     * 删
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void delUser(@Param("id")Integer id);

    /**
     * 改
     * @param user
     */
    @Update("update user set username=#{user.username},password=#{user.password},money=#{user.money} where id=#{user.id}")
    void updateUser(@Param("user") User user);

    /**
     * 查
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
    @Select("select * from user where username=#{user.username} and password=#{user.password}")
    User login(@Param("user") User user);
    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username") String username);
}
