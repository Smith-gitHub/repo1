package com.achp.controller;


import com.achp.domain.User;
import com.achp.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public @ResponseBody List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/login")
    public @ResponseBody User login(@RequestBody User user){
        return userService.login(user);
    }
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        
        userService.saveUser(user);
        return "index";
    }
    @RequestMapping("/delUser")
    public void delUser(Integer id){
        userService.delUser(id);
    }
    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
    @RequestMapping("/findOne")
    public @ResponseBody User findByName(String username){
        return userService.findByUsername(username);
    }
}
