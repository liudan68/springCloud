package com.dandan.springcloud;

import com.dandan.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author dandan
 * @create 2020/8/16 9:58
 */
public interface PaymentService {

    public int create(Payment payment);  //新增数据

    public Payment getPaymentById(@Param("id") long id);
}
