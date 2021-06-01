package cn.os.common.enums;

import lombok.Getter;

/**
 * @Author ou shuo
 * @Date 2021/5/19 14:46
 * @description  返回消息枚举类
 */
@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "服务器内部错误"),
    ACCOUNT_EXIST(1001, "账号已存在"),
    ACCOUNT_DISABLED(1002, "账号不可用，请联系管理员"),
    REGISTER_SUCCESS(1003, "注册成功"),
    REGISTER_FAIL(1004, "注册失败"),
    USER_NOT_EXIST(1005, "用户不存在"),
    USER_PASSWORD_ERROR(1006, "密码错误"),
    INSERT_SUCCESS(200, "添加成功"),
    INSERT_FAIL(500, "添加失败"),
    UPDATE_SUCCESS(200, "修改成功"),
    UPDATE_FAIL(500, "修改失败"),
    DELETE_SUCCESS(200, "删除成功"),
    DELETE_FAIL(500, "删除失败");

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;
}
