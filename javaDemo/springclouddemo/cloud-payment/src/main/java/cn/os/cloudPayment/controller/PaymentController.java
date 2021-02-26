package cn.os.cloudPayment.controller;

import cn.os.cloudPayment.common.result.ResultCode;
import cn.os.cloudPayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResultCode findAll() {
        return paymentService.findAll();
    }
}
