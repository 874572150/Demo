package com.os.myframe.service;

import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResultCode register(User user);

    ResultCode login(User user);

    List<User> getUserList(User user, Integer page, Integer pageSize);
}
