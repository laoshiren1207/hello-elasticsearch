package com.laoshiren.hello.elasticsearch.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider
 * ClassName:       ProviderMybatisElasticApplication
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/13 13:05
 * Version:         1.0.0
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.laoshiren.hello.elasticsearch.provider.mapper")
public class ProviderMybatisElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMybatisElasticApplication.class,args);
    }

}
