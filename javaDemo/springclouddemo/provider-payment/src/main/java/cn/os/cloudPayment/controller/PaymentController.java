package cn.os.cloudPayment.controller;

import cn.os.cloudPayment.common.result.ResultCode;
import cn.os.cloudPayment.common.result.ResultStatus;
import cn.os.cloudPayment.pojo.Payment;
import cn.os.cloudPayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResultCode findAll() {
        return ResultCode.setSuccess(paymentService.findAll());
    }

    @PostMapping
    public ResultCode save(@RequestBody Payment payment) {
        boolean res = paymentService.save(payment);
        return ResultCode.setSuccess(ResultStatus.INSERT_SUCCESS);
    }
}
