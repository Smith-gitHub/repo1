package com.achp.service.impl;

import com.achp.dao.UserDao;
import com.achp.domain.Role;
import com.achp.domain.UserInfo;
import com.achp.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        if (roles.isEmpty()){
            list.add(new SimpleGrantedAuthority("_"));
        }else {
            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
        }

        return list;

    }

    @Override
    public List<UserInfo> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();

    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //加密
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String userId) throws Exception {
        return userDao.findById(userId);
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);

    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {

        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> findRoles(String userId) throws Exception {


        return userDao.findRoles(userId);
    }

    @Override
    public void delRole(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {

            userDao.delRole(userId,roleId);
        }
    }

    @Override
    public void update(UserInfo user) throws Exception {
        userDao.update(user);
    }
}
