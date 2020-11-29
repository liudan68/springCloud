package com.dandan.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author dandan
 * @create 2020/8/26 8:18
 */
@RestController
@Slf4j
public class OrderConsulController {
   public static final String INVOKE_URL = "http://consul-provider-payment";

   @Resource
   private RestTemplate restTemplate;

   @GetMapping(value = "/consumer/payment/consul")
   public String paymentInfo(){
       String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul", String.class);
       return result;
   }
}
