package com.achp.service;

import com.achp.domain.Permission;
import com.achp.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll(int page, int size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findOtherPermissions(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] ids) throws Exception;

    List<Permission> findPermissions(String id) throws Exception;

    void delPermission(String roleId, String[] ids);
}
