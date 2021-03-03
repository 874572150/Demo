package cn.os.order.controller;

import cn.os.order.common.result.ResultCode;
import cn.os.order.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Result;

/**
 * @author oushuo
 * @date 2021/3/2
 * @description TODO
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://localhost:9001";

//    @GetMapping
//    public ResultCode create(Payment payment) {
//        return restTemplate.postForObject(PAYMENT_URL + "", payment, ResultCode.class);
//    }

    @GetMapping
    public ResultCode findAll() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment", ResultCode.class);
    }

    @GetMapping("/save")
    public ResultCode save(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL+"/payment",payment,ResultCode.class);
    }
}
