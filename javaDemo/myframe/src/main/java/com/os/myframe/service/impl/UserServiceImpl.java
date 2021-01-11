package com.os.myframe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.os.myframe.common.config.result.ResultCode;
import com.os.myframe.common.config.result.ResultStatus;
import com.os.myframe.common.utils.EncryptUtils;
import com.os.myframe.common.utils.JpaSpecificationUtil;
import com.os.myframe.common.utils.JwtUtils;
import com.os.myframe.common.utils.SearchCondition;
import com.os.myframe.model.User;
import com.os.myframe.repository.UserRespository;
import com.os.myframe.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        ResultCode resultCode = new ResultCode();
        User existUser = new User();
        existUser.setAccount(user.getAccount());
        Optional<User> userOptional = userRespository.findOne(Example.of(existUser));
        ;
        if (userOptional.isPresent()) {
            resultCode.setFail("该账号已存在");
        } else {
            user.setPassword(EncryptUtils.SHA512(user.getPassword()));
            User saveUser = userRespository.save(user);
            if (saveUser == null) {
                resultCode.setFail("注册异常");
            } else {
                resultCode.setSuccess("注册成功");
            }
        }
        return resultCode;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public ResultCode login(User user) {
        ResultCode resultCode = new ResultCode();
        User loginUser = new User();
        loginUser.setAccount(user.getAccount());
        Optional<User> userOptional = userRespository.findOne(Example.of(loginUser));
        User ret = null;
        if (userOptional.isPresent()) {
            ret = userOptional.get();
        }
        if (ret == null) {
            return ResultCode.setFail(ResultStatus.USER_NOT_EXIST);
        }
        boolean res = BCrypt.checkpw(EncryptUtils.SHA512(user.getPassword()), ret.getPassword());
        if (!res) {
            return ResultCode.setFail(ResultStatus.USER_PASSWORD_ERROR);
        }
        // 若登录成功，签发 JWT token
        String jwtToken = JwtUtils.sign(user.getAccount(), JwtUtils.SECRET);
        // 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
        response.setHeader(JwtUtils.AUTH_HEADER, jwtToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", ret.getId());
        jsonObject.put("nickname", ret.getNickname());

        resultCode.setSuccess(jsonObject);

        return resultCode;
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
    public List<User> getUserList(User user, Integer page, Integer pageSize) {
        List<SearchCondition> searchConditions = new ArrayList<>();
        searchConditions.add(new SearchCondition("account",SearchCondition.OprEnum.EQ,"zs"));
        Specification<User> specification = JpaSpecificationUtil.createSpecification(searchConditions, User.class);
        return null;
    }
}
