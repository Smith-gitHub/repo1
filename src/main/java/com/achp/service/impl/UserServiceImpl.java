package com.achp.service.impl;

import com.achp.dao.UserDao;
import com.achp.domain.User;
import com.achp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void delUser(Integer id) {
        userDao.delUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}
