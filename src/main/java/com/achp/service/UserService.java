package com.achp.service;

import com.achp.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserService {

    /**
     * 添加
     * @param user
     */
    void saveUser(User user);

    /**
     * 删除
     * @param id
     */
    void delUser(Integer id);

    /**
     * 更新
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询
     * @return
     */
    List<User> findAll();

    User findByUsername(String username);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

}
