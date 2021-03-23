package cn.os.cloudPayment.service;

import cn.os.cloudPayment.common.result.ResultCode;
import cn.os.cloudPayment.pojo.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    boolean save(Payment payment);
}
