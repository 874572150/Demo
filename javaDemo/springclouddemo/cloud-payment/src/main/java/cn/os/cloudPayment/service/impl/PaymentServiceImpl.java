package cn.os.cloudPayment.service.impl;

import cn.os.cloudPayment.common.result.ResultCode;
import cn.os.cloudPayment.dao.PaymentDao;
import cn.os.cloudPayment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public ResultCode findAll() {
        return ResultCode.setSuccess(paymentDao.findAll());
    }
}
