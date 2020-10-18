package com.laoshiren.hello.elasticsearch.provider.controller;

import com.laoshiren.hello.elasticsearch.provider.api.OrganizationService;
import com.laoshiren.hello.elasticsearch.provider.domain.Organization;
import com.laoshiren.hello.elasticsearch.provider.domain.MyPageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.controller
 * ClassName:       OrganizationController
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/14 9:25
 * Version:         1.0.0
 */
@RestController
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    @GetMapping("/search/{pageNum}/{pageSize}")
    public MyPageInfo<Organization> search(@PathVariable(value = "pageNum")int pageNum,
                                           @PathVariable(value = "pageSize")int pageSize,
                                           @RequestParam(value = "word",required=false)String word) throws Exception{
        // word 不能直接中文需要进行一次url编码
        return organizationService.queryOrganizationHighLight(pageNum,pageSize,word);
    }

}
