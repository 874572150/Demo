package com.os.myframe.model;

import lombok.Data;

/**
 * @author oushuo
 * @date 2020/11/18
 * @description 请求日志实体类
 */
@Data
public class RequestInfo {
    private String ip;

    private String url;

    private String httpMethod;

    private String classMethod;

    private Object requestParams;

    private Object result;

    private Long timeCost;
}
