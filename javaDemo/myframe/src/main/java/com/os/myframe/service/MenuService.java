package com.os.myframe.service;

import com.os.myframe.common.config.result.ResultCode;

public interface MenuService {
    ResultCode list(Integer page, Integer pageSize);
}
