package com.laoshiren.hello.elasticsearch.provider.configure;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.configure
 * ClassName:       ElasticSearchClientConfiguration
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/9 16:21
 * Version:         1.0.0
 */
@Configuration
public class ElasticSearchClientConfiguration {

    @Value("${laoshiren.elastic.hostname}")
    private String hostname;
    @Value("${laoshiren.elastic.port}")
    private int port;
    @Value("${laoshiren.elastic.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostname, port, scheme)));
    }

}
