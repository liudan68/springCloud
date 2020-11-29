package com.dandan.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author dandan
 * @create 2020/9/1 12:55
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName()+ "paymentInfo_OK,id" + id + "\t";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3000;
        try{
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        }catch (InterruptedException e){e.printStackTrace();}
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "耗时（秒）" + timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler,id" + id + "\t" + "呃呃呃，系统开小差了";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" ,commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "1000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("***** id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID(); //生成唯一标识符
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;

    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，当前ID为："+ id;
    }
}
