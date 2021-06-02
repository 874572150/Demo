package cn.spring;

/**
 * @author oushuo
 * @date 2021/6/222:07
 * @description ${TODO}
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInittialization(Object bean, String beanName);
}
