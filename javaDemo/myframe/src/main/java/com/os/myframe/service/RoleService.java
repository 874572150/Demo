package com.os.myframe.service;


import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.Role;

public interface RoleService {
    ResultCode getRoleList(Role role, Integer page, Integer pageSize);

    ResultCode insertRole(Role role);

    ResultCode updateRole(Role role);
}
