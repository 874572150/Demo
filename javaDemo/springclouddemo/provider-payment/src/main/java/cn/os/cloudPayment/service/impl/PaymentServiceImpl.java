package cn.os.cloudPayment.service.impl;

import cn.os.cloudPayment.common.result.ResultCode;
import cn.os.cloudPayment.dao.PaymentDao;
import cn.os.cloudPayment.pojo.Payment;
import cn.os.cloudPayment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    public boolean save(Payment payment) {
        return paymentDao.save(payment);
    }
}
