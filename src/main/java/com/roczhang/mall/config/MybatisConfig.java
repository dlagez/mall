package com.roczhang.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 的配置类
 */
@Configuration
@MapperScan({"com.roczhang.mall.mbg.mapper", "com.roczhang.mall.dao"})
public class MybatisConfig {
}
