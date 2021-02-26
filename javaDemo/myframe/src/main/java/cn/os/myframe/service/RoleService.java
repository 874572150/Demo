package cn.os.myframe.service;


import cn.os.myframe.model.Role;
import cn.os.myframe.common.config.result.ResultCode;

public interface RoleService {
    ResultCode getRoleList(Role role, Integer page, Integer pageSize);

    ResultCode insertRole(Role role);

    ResultCode updateRole(Role role);
}
