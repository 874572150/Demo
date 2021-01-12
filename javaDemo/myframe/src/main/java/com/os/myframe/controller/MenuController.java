package com.os.myframe.controller;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list/{page}/{pageSize}")
    public ResultCode list(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "pageSize") Integer pageSize) {
        return menuService.list(page, pageSize);
    }

}
