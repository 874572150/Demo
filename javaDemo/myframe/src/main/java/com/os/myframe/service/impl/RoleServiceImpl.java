package com.os.myframe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.common.config.result.ResultStatus;
import com.os.myframe.model.Role;
import com.os.myframe.repository.RoleRepository;
import com.os.myframe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author oushuo
 * @date 2021/1/11
 * @description 角色服务层
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 获取角色列表
     *
     * @param role
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResultCode getRoleList(Role role, Integer page, Integer pageSize) {
        JSONObject jsonObject = new JSONObject();
        role = role == null ? new Role() : role;
        role.setEnabled(true);
        List<Role> roleList = null;
        if (page == null && pageSize == null) {
            roleList = roleRepository.findAll(Example.of(role));
        } else {
            Page<Role> rolePage = roleRepository.findAll(Example.of(role), PageRequest.of(page, pageSize));
            roleList = rolePage.getContent();
            jsonObject.put("totalElements", rolePage.getTotalElements());
        }
        jsonObject.put("list", roleList);
        return ResultCode.setSuccess(jsonObject);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public ResultCode insertRole(Role role) {
        Role save = roleRepository.save(role);
        return save != null ?
                ResultCode.setFail(ResultStatus.INSERT_SUCCESS) :
                ResultCode.setFail(ResultStatus.INSERT_FAIL);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Override
    public ResultCode updateRole(Role role) {
        Role save = roleRepository.save(role);
        return save != null ?
                ResultCode.setFail(ResultStatus.UPDATE_SUCCESS) :
                ResultCode.setFail(ResultStatus.UPDATE_FAIL);
    }
}
