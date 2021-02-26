package cn.os.myframe.shiro.controller;

import cn.os.myframe.common.utils.JwtUtils;
import cn.os.myframe.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author oushuo
 * @date 2020/10/9
 * @description TODO
 */
@RestController
@RequestMapping("/shiro")
public class ShiroLoginController {
    @Autowired
    private HttpServletResponse response;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(), user.getPassword());
            subject.login(usernamePasswordToken);
            // 若登录成功，签发 JWT token
            String jwtToken = JwtUtils.sign(user.getAccount(), JwtUtils.SECRET);
            // 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
            response.setHeader(JwtUtils.AUTH_HEADER, jwtToken);
            return "login Success";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("帐号不存在!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        }

        return "Login Fail";
    }

    @PostMapping("/index")
    @RequiresPermissions("/user/index")
    public Object index() {
        return "index";
    }

}
