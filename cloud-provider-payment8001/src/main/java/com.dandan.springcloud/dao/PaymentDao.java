package com.dandan.springcloud.dao;

import com.dandan.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author dandan
 * @create 2020/8/16 9:01
 */

@Mapper
public interface PaymentDao {
    public int create(Payment payment);  //新增数据

    public Payment getPaymentById(@Param("id")Long id);
}
