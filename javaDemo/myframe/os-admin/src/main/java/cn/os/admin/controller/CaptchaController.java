package cn.os.admin.controller;

import cn.os.common.result.ResultCode;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author ou shuo
 * @Date 2021/5/14 16:34
 * @TODO 验证码控制层
 */
@Api(value = "验证码 控制器",tags = "验证码 控制器")
@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    // 验证码类型
    @Value("${os.captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @ApiOperation(value = "生成验证码")
    @GetMapping("/captchaImage")
    public ResultCode getCode(HttpServletResponse response) throws IOException {
        // 保存验证码信息
//        String uuid = IdUtils.simpleUUID();
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

//        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return ResultCode.error(e.getMessage());
        }

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("uuid", uuid);
        jsonObject.put("img", Base64.encode(os.toByteArray()));
        return ResultCode.success(jsonObject);
    }

}
