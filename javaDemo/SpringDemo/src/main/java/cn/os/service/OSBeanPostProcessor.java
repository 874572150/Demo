package cn.os.service;

import cn.spring.BeanPostProcessor;
import cn.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author oushuo
 * @date 2021/6/222:09
 * @description ${TODO}
 */
@Component
public class OSBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        System.out.println("初始化前");
//        if (beanName.equals("userService")) {
//            ((UserServiceImpl) bean).setName("test");
//        }
        return bean;
    }

    @Override
    public Object postProcessAfterInittialization(Object bean, String beanName) {
        System.out.println("初始化后");
        if (beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(OSBeanPostProcessor.class.getClassLoader(), bean.getClass()
                    .getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑");
                    return method.invoke(bean, args);
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
