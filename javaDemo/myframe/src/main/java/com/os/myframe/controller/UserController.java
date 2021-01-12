package com.os.myframe.controller;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.User;
import com.os.myframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author oushuo
 * @date 2021/1/11
 * @description 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResultCode register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultCode login(User user) {
        return userService.login(user);
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list/{page}/{pageSize}")
    public ResultCode getUserList(@ModelAttribute User user,
                                  @PathVariable(name = "page") Integer page,
                                  @PathVariable(name = "pageSize") Integer pageSize) {
        return userService.getUserList(user, page, pageSize);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public ResultCode insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping
    public ResultCode updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
