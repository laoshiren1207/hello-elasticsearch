package com.laoshiren.hello.elasticsearch.provider.quartz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.laoshiren.hello.elasticsearch.provider.api.OrganizationService;
import com.laoshiren.hello.elasticsearch.provider.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.quartz
 * ClassName:       OrganizationQuartz
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/13 18:20
 * Version:         1.0.0
 */
@Component
@Slf4j
public class OrganizationQuartz {

    @Resource
    private OrganizationService service;
    @Resource
    private ObjectMapper jsonMapper;
    @Resource
    private RestHighLevelClient client;

    /**
     * 定时任务 job
     */
    @Scheduled(cron = " */3 * * * * ? ")
    public void addOrganization() throws IOException {
        log.info("开始同步信息----");
        PageInfo<Organization> page = service.selectPage(0, 1000, null);
        BulkRequest bulkRequest = new BulkRequest();
        // 批量操作
        bulkRequest.timeout(TimeValue.timeValueSeconds(30));
        for (int i = 0; i< page.getList().size(); i++) {
            //批处理请求
            Organization organization = page.getList().get(i);
            IndexRequest index = new IndexRequest("organization")
                    .id("" + (organization.getOrgId()))
                    // 转换json string
                    .source(jsonMapper.writeValueAsString(organization), XContentType.JSON);
            bulkRequest.add(index);
        }
        if ( page.getList().size()>0) {
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("同步完成----- {} --是否存在失败-- {} ",bulk.status(),bulk.hasFailures());
        }
    }

}
