package com.os.myframe.common.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    SYSTEM_UNAUTHENICATION_ERROR("401", "用户未认证"),
    SYSTEM_UNAUTHORIZED_ERROR("403", "用户无权限"),
    SYSTEM_TOKEN_VALIDATOR_ERROR("", "Token验证错误"),
    SYSTEM_ALREADY_LOGIN("error_login", "您的账户已在其他终端登录");

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
