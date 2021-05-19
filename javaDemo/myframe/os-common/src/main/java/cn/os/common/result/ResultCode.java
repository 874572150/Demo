package cn.os.common.result;

import cn.os.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author oushuo
 * @date 2020/09/27
 * @description 封装统一的响应信息
 */
@Data
public class ResultCode implements Serializable {
    // 状态码
    private Integer status;

    // 响应值
    private String message;

    // 响应体
    private Object data;

    // 时间戳
    private Long timestamp = System.currentTimeMillis();

    private ResultCode() {}

    public static ResultCode success() {
        return ResultCode.success(ResultEnum.SUCCESS);
    }

    public static ResultCode success(Object data) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultEnum(ResultEnum.SUCCESS);
        resultCode.setData(data);
        return resultCode;
    }

    public static ResultCode error() {
        return ResultCode.error(ResultEnum.FAIL);
    }

    public static ResultCode error(ResultEnum resultEnum) {
        ResultCode resultCode = new ResultCode();
        resultCode.setResultEnum(resultEnum);
        return resultCode;
    }

    public static ResultCode error(String message) {
        ResultCode resultCode = new ResultCode();
        resultCode.setMessage(message);
        return resultCode;
    }

    private void setResultEnum(ResultEnum resultEnum) {
        this.status = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
