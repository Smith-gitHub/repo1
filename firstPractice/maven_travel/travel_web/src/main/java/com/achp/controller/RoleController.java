package com.achp.controller;

import com.achp.domain.Permission;
import com.achp.domain.Role;
import com.achp.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "5") int size ) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;

    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception{

        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();

        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception{
        ModelAndView mv = new ModelAndView();

        Role role = roleService.findById(id);
        List<Permission> otherPermissions = roleService.findOtherPermissions(id);

        mv.addObject("roleInfo",role);
        mv.addObject("otherPermission",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/findPermissionByRoleId")
    public ModelAndView delPermissionByRoleId(String id) throws Exception{
        ModelAndView mv = new ModelAndView();

        Role role = roleService.findById(id);
        List<Permission> permissions = roleService.findPermissions(id);

        mv.addObject("role_permission",role);
        mv.addObject("permission",permissions);
        mv.setViewName("role-permission-del");
        return mv;
    }

    @RequestMapping("/delPermission")
    public String delPermission(@RequestParam(name = "roleId",required = true) String roleId, @RequestParam(name = "ids",required = true) String[] ids) throws Exception{
        roleService.delPermission(roleId, ids);
        return "redirect:findAll";
    }


    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId, @RequestParam(name = "ids",required = true) String[] ids) throws Exception {
        roleService.addPermissionToRole(roleId, ids);
        return "redirect:findAll";
    }

}
