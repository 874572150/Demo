package com.os.myframe.service;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.Menu;

public interface MenuService {
    ResultCode list(Integer page, Integer pageSize);

    ResultCode deleteMenu(Menu menu);

    ResultCode updateMenu(Menu menu);

    ResultCode insertMenu(Menu menu);
}
