package com.dandan.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dandan
 * @create 2020/10/25 23:03
 */
@Configuration
@MapperScan("com.dandan.springcloud.dao")
public class MybatisConfig {
}
