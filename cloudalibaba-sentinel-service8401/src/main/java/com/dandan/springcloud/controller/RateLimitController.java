package com.dandan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dandan.springcloud.entities.CommonResult;
import com.dandan.springcloud.entities.Payment;
import com.sun.deploy.security.BlockedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dandan
 * @create 2020/10/12 13:16
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockedException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t服务不可用",null);
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流测试OK",new Payment(2020L,"serial002"));
    }
}
