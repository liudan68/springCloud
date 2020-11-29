package com.dandan.springcloud.impl;

import com.dandan.springcloud.dao.PaymentDao;
import com.dandan.springcloud.entities.Payment;
import com.dandan.springcloud.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dandan
 * @create 2020/8/16 10:01
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
