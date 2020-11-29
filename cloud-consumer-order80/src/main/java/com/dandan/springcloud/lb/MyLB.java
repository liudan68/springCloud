package com.dandan.springcloud.lb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import javax.crypto.spec.PSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dandan
 * @create 2020/8/27 13:49
 */
@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(-1);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get(); //获取当前初始值并赋给current
            next = current >= 2147483647? 0: current + 1;
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("***** 第几次访问，次数为 next:" + next);
        return next;
    }

    @Autowired
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
