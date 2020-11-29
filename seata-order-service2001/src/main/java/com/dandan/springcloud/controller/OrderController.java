package com.dandan.springcloud.controller;

import com.dandan.springcloud.domain.CommonResult;
import com.dandan.springcloud.domain.Order;
import com.dandan.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dandan
 * @create 2020/10/24 22:24
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
