package com.os.myframe.service.impl;

import com.os.myframe.common.utils.TreeUtil;
import com.os.myframe.model.Menu;
import com.os.myframe.repository.MenuRepository;
import com.os.myframe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> list() {
        List<Menu> menus = menuRepository.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("level",1);
        List<Menu> menusTree = TreeUtil.dataToTree(menus, map,"id","parentId","children", Menu.class);
        return menusTree;
    }
}
