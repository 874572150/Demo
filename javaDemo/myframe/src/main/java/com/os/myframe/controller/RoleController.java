package com.os.myframe.controller;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.Role;
import com.os.myframe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

/**
 * @author oushuo
 * @date 2021/1/11
 * @description 角色控制层
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表
     * @param role
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/list/{page}/{pageSize}", "/list"})
    public ResultCode getRoleList(@ModelAttribute Role role,
                                  @RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer pageSize) {
        return roleService.getRoleList(role, page, pageSize);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping
    public ResultCode insertRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PutMapping
    public ResultCode updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

}