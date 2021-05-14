package cn.os.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ou shuo
 * @Date 2021/5/14 16:34
 * @TODO 验证码控制层
 */
@RestController
public class CaptchaController {
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Object getCode(HttpServletResponse response) throws IOException {
        return null;
    }

}
