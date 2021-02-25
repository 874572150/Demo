package com.os.myframe.controller;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.Menu;
import com.os.myframe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list/{page}/{pageSize}")
    public ResultCode list(@RequestParam(name = "page") Integer page,
                           @RequestParam(name = "pageSize") Integer pageSize) {
        return menuService.list(page, pageSize);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping("")
    public ResultCode insertMenu(@RequestBody Menu menu) {
        return menuService.insertMenu(menu);
    }

    /**
     * 修改菜单
     */
    @PutMapping("")
    public ResultCode updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("")
    public ResultCode deleteMenu(@RequestBody Menu menu) {
        return menuService.deleteMenu(menu);
    }

}
