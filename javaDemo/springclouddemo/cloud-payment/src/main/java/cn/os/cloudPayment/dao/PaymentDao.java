package cn.os.cloudPayment.dao;

import cn.os.cloudPayment.pojo.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao {
    List<Payment> findAll();
}
