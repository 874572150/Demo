package cn.os.myframe.service.impl;

import cn.os.myframe.service.UserService;
import com.alibaba.fastjson.JSONObject;
import cn.os.myframe.common.config.result.ResultCode;
import cn.os.myframe.common.config.result.ResultStatus;
import cn.os.myframe.common.utils.EncryptUtils;
import cn.os.myframe.common.utils.JwtUtils;
import cn.os.myframe.model.User;
import cn.os.myframe.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRespository userRespository;

    @Autowired
    private HttpServletResponse response;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public ResultCode register(User user) {
        // 查询当前账户是否已存在
        User byAccount = userRespository.findByAccount(user.getAccount());
        if (byAccount != null) { // 账号已存在
            return ResultCode.setFail(ResultStatus.ACCOUNT_EXIST);
        } else if (!byAccount.getEnabled()) { // 账号不可用
            return ResultCode.setFail(ResultStatus.ACCOUNT_DISABLED);
        }
        // 保存用户
        user.setPassword(EncryptUtils.SHA512(user.getPassword()));
        User saveUser = userRespository.save(user);
        if (saveUser == null) {
            return ResultCode.setFail(ResultStatus.REGISTER_FAIL);
        }
        return ResultCode.setSuccess(ResultStatus.REGISTER_SUCCESS);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public ResultCode login(User user) {
        User loginUser = userRespository.findByAccount(user.getAccount());
        if (loginUser == null) { // 用户不存在
            return ResultCode.setFail(ResultStatus.USER_NOT_EXIST);
        } else if (!loginUser.getEnabled()) {
            return ResultCode.setFail(ResultStatus.ACCOUNT_DISABLED);
        }
//        boolean res = BCrypt.checkpw(EncryptUtils.SHA512(user.getPassword()), loginUser.getPassword());
        boolean res = loginUser.getPassword().equals(EncryptUtils.SHA512(user.getPassword()));
        if (!res) {
            return ResultCode.setFail(ResultStatus.USER_PASSWORD_ERROR);
        }
        // 若登录成功，签发 JWT token
        String jwtToken = JwtUtils.sign(user.getAccount(), JwtUtils.SECRET);
        // 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
        response.setHeader(JwtUtils.AUTH_HEADER, jwtToken);
        return ResultCode.setSuccess(loginUser);
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResultCode getUserList(User user, Integer page, Integer pageSize) {
        user = user == null ? new User() : user;
        user.setEnabled(true);
        JSONObject jsonObject = new JSONObject();
        Page<User> userPage = userRespository.findAll(Example.of(user), PageRequest.of(page, pageSize));
        jsonObject.put("list", userPage.getContent());
        jsonObject.put("totalElements", userPage.getTotalElements());
        return ResultCode.setSuccess(jsonObject);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public ResultCode insertUser(User user) {
        User save = userRespository.save(user);
        return save != null ?
                ResultCode.setFail(ResultStatus.INSERT_SUCCESS) :
                ResultCode.setFail(ResultStatus.INSERT_FAIL);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public ResultCode updateUser(User user) {
        User save = userRespository.save(user);
        return save != null ?
                ResultCode.setFail(ResultStatus.UPDATE_SUCCESS) :
                ResultCode.setFail(ResultStatus.UPDATE_FAIL);
    }
}
