package com.os.myframe.common.config.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author oushuo
 * @date 2020/09/27
 * @description 封装统一的响应信息
 */
@Setter
@Getter
public class ResultCode implements Serializable {
    // 状态码
    private Integer status;

    // 响应值
    private String message;

    // 响应体
    private Object data;

    // 时间戳
    private Long timestamp = System.currentTimeMillis();

    public ResultCode() {}

    public ResultCode(ResultStatus resultStatus, Object data) {
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    public static ResultCode setSuccess() {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultStatus(ResultStatus.SUCCESS);
        return resultCode;
    }

    public static ResultCode setSuccess(Object data) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultStatus(ResultStatus.SUCCESS);
        resultCode.setData(data);
        return resultCode;
    }

    public static ResultCode setFail(Object data) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultStatus(ResultStatus.FAIL);
        return resultCode;
    }

    public static ResultCode setFail(ResultStatus resultStatus) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultStatus(resultStatus);
        return resultCode;
    }

    public static ResultCode setFail(ResultStatus resultStatus, Object data) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultStatus(resultStatus);
        resultCode.setData(data);
        return resultCode;
    }

    private void setResultStatus(ResultStatus resultStatus) {
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
    }
}
