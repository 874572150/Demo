package cn.os.test.service;


import cn.os.test.common.config.result.ResultCode;
import cn.os.test.model.Role;

public interface RoleService {
    ResultCode getRoleList(Role role, Integer page, Integer pageSize);

    ResultCode insertRole(Role role);

    ResultCode updateRole(Role role);
}
