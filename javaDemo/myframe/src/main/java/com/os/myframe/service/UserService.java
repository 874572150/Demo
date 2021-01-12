package com.os.myframe.service;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResultCode register(User user);

    ResultCode login(User user);

    ResultCode getUserList(User user, Integer page, Integer pageSize);

    ResultCode insertUser(User user);

    ResultCode updateUser(User user);
}
