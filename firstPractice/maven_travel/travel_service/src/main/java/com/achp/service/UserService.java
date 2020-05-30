package com.achp.service;


import com.achp.domain.Role;
import com.achp.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {


    List<UserInfo> findAll(int page, int size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String userId) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    List<Role> findRoles(String userId) throws Exception;

    void delRole(String userId, String[] roleIds) throws Exception;

    void update(UserInfo user) throws Exception;
}
