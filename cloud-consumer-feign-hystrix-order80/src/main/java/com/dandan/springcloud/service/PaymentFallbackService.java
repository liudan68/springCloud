package com.dandan.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author dandan
 * @create 2020/9/3 21:44
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    public String paymentInfo_OK(Integer id) {
        return "----- PaymentFallbackService fall back-----paymentInfo_OK";
    }

    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-----paymentInfo_TimeOut";
    }
}
