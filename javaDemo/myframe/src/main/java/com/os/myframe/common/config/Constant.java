package com.os.myframe.common.config;


/**
 * @author oushuo
 * @date 2020/10/20
 * @description 常量
 */
public class Constant {
    /**
     * Cookie中token的定义key
     */
    public final static String COOKIE_TOKEN_KEY = "sharing_platform_token";

    /**
     * 枚举，定义token的三种状态
     *
     */
    public static enum TokenStatus {
        /**
         * 过期
         */
        EXPIRED,
        /**
         * 无效(token不合法)
         */
        INVALID,
        /**
         * 有效的
         */
        VALID
    }

    //半小时，单位毫秒
    public final static Integer HALFHOUR_BY_MIL = 1000 * 60 * 30;

    //半小时，单位秒
    public final static Integer HALFHOUR_BY_SEC =  60 * 30;

    //token过期时间(30分)（有用）
    public static final Integer TOKEN_TIMEOUT_TIMES = 1000 * 60 * 30;

    //cookie过期时间
    public static final Integer COOKIES_TIMEOUT_TIMES = 60 * 30;

    //redis 用户信息的数据过期时间 (单位秒)
    public static final Integer REDIS_USERINFO_TIMEOUT_TIMES = 60 * 30;

}
