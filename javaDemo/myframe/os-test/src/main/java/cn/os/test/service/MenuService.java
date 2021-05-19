package cn.os.test.service;

import cn.os.test.common.config.result.ResultCode;
import cn.os.test.model.Menu;

public interface MenuService {
    ResultCode list(Integer page, Integer pageSize);

    ResultCode deleteMenu(Menu menu);

    ResultCode updateMenu(Menu menu);

    ResultCode insertMenu(Menu menu);
}
