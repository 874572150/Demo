package cn.os.test.common.utils;

import java.lang.annotation.*;

/**
 * @Author ou shuo
 * @Date 2021/5/12 13:52
 * @TODO 树结构注解
 */
@Target(value= {ElementType.FIELD})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TreeElement {
    String name();
}
