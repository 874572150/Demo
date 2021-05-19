package cn.os.test.service;

import cn.os.test.common.config.result.ResultCode;
import cn.os.test.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResultCode register(User user);

    ResultCode login(User user);

    ResultCode getUserList(User user, Integer page, Integer pageSize);

    ResultCode insertUser(User user);

    ResultCode updateUser(User user);
}
