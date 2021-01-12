package com.os.myframe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.Menu;
import com.os.myframe.repository.MenuRepository;
import com.os.myframe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public ResultCode list(Integer page, Integer pageSize) {
        JSONObject jsonObject = new JSONObject();
        Page<Menu> menuPage = menuRepository.findAll(PageRequest.of(page, pageSize));
        jsonObject.put("list", menuPage.getContent());
        jsonObject.put("totalElements", menuPage.getTotalElements());
        return ResultCode.setSuccess(jsonObject);
    }
}
