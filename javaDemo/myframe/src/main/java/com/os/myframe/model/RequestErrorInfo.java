package com.os.myframe.model;

import lombok.Data;

/**
 * @author oushuo
 * @date 2020/11/18
 * @description 异常请求实体类
 */
@Data
public class RequestErrorInfo {
    private String ip;

    private String url;

    private String httpMethod;

    private String classMethod;

    private Object requestParams;

    private RuntimeException exception;
}
