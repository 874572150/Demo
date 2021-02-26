package cn.os.myframe.service;

import cn.os.myframe.model.Menu;
import cn.os.myframe.common.config.result.ResultCode;

public interface MenuService {
    ResultCode list(Integer page, Integer pageSize);

    ResultCode deleteMenu(Menu menu);

    ResultCode updateMenu(Menu menu);

    ResultCode insertMenu(Menu menu);
}
