package com.dandan.springcloud.dao;

import com.dandan.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author dandan
 * @create 2020/10/21 13:09
 */
@Mapper
public interface OrderDao {
    //创建订单
    void create(Order order);

    //更新订单状态
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
