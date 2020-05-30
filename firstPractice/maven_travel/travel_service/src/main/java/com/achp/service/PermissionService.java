package com.achp.service;

import com.achp.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll(int page, int size) throws Exception;

    void save(Permission permission) throws Exception;
}
