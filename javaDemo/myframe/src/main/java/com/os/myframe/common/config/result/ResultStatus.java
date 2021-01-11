package com.os.myframe.common.config.result;

import lombok.Getter;

/**
 * @author oushuo
 * @date 2020/09/27
 * @description 响应状态码
 */
@Getter
public enum ResultStatus {
    SUCCESS(200,"成功"),FAIL(500,"后台错误"),USER_NOT_EXIST(1001,"用户不存在"),USER_PASSWORD_ERROR(1002,"密码错误");

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

}
