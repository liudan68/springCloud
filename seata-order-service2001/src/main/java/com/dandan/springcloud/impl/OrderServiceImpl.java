package com.dandan.springcloud.impl;

import com.dandan.springcloud.dao.OrderDao;
import com.dandan.springcloud.domain.Order;
import com.dandan.springcloud.service.AccountService;
import com.dandan.springcloud.service.OrderService;
import com.dandan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dandan
 * @create 2020/10/23 13:09
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    public void create(Order order) {
        log.info("------>开始创建订单");
        orderDao.create(order);

        log.info("------>订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("------>账户微服务开始调用账户，做扣减");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("----->修改订单状态");
        orderDao.update(order.getUserId(), 0);
    }
}
