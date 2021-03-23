package cn.os.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author oushuo
 * @date 2021/3/2
 * @description TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderAplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderAplication.class, args);
    }
}
