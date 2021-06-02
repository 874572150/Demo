package cn.os;

import cn.os.service.OrderService;
import cn.os.service.UserService;
import cn.os.service.UserServiceImpl;
import cn.spring.OSApplicationContext;

/**
 * @author oushuo
 * @date 2021/6/121:04
 * @description ${TODO}
 */
public class Test {
    public static void main(String[] args) {
        OSApplicationContext applicationContext = new OSApplicationContext(AppConfig.class);
//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));
//        UserServiceImpl userServiceImpl = (UserServiceImpl) applicationContext.getBean("userService");
//        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
//        userServiceImpl.test();
//        orderService.test();
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
