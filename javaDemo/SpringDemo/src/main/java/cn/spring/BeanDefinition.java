package cn.spring;

/**
 * @author oushuo
 * @date 2021/6/121:57
 * @description ${TODO}
 */
public class BeanDefinition {

    private Class clazz;

    private String scope;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
