package com.os.myframe.controller;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.User;
import com.os.myframe.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/index")
    @RequiresPermissions("/user/index")
    public Object index(@RequestBody User user) {
        return "index";
    }

    @GetMapping("/user")
    public User getUser(@ModelAttribute User user) {
        return user;
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/list/{page}/{pageSize}","/list"})
    public List<User> getUserList(@ModelAttribute User user, @PathVariable(name = "page", required = false) Integer page, @PathVariable(name = "page", required = false) Integer pageSize) {
        return userService.getUserList(user, page, pageSize);
    }
}
