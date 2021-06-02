package cn.os.service;

import cn.spring.*;

/**
 * @author oushuo
 * @date 2021/6/121:09
 * @description ${TODO}
 */
@Component("userService")
//@Scope("prototype")
public class UserServiceImpl implements UserService {
    @Autowired
    private OrderService orderService;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void test() {
        System.out.println("orderService:" + orderService);
        System.out.println("name:" + name);
    }
}
