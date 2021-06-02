package cn.spring;

/**
 * @author oushuo
 * @date 2021/6/221:42
 * @description ${TODO}
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
