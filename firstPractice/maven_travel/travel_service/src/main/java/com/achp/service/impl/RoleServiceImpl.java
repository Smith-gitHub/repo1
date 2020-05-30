package com.achp.service.impl;

import com.achp.dao.RolesDao;
import com.achp.domain.Permission;
import com.achp.domain.Role;
import com.achp.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesDao rolesDao;
    @Override
    public List<Role> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return rolesDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        rolesDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return rolesDao.findByRoleId(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception {
        return rolesDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String id : ids) {
            rolesDao.addPermissionToRole(roleId,id);

        }
    }

    @Override
    public List<Permission> findPermissions(String id) throws Exception {

        return rolesDao.findPermissions(id);
    }

    @Override
    public void delPermission(String roleId, String[] ids) {
        for (String id : ids) {
            rolesDao.delPermission(roleId,id);
        }


    }
}
