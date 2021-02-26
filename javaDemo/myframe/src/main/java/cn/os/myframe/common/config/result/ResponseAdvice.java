package cn.os.myframe.common.config.result;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author oushuo
 * @date 2020/09/07
 * @description 拦截Controller的返回结果
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 这个方法表示对于哪些请求要执行 beforeBodyWrite，返回 true 执行，返回 false 不执行
     * @param  returnType, converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println("ResponseAdvice supports...");
        System.out.println(returnType);
        System.out.println(converterType);
        return !converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("ResponseAdvice beforeBodyWrire...");
        System.out.println(body);
        System.out.println(returnType);
        System.out.println(selectedContentType);
        if (body instanceof ResultCode) {
            return body;
        } else {
            return ResultCode.setSuccess(body);
        }
    }
}
