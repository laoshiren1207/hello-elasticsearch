package com.laoshiren.hello.elasticsearch.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.laoshiren.hello.elasticsearch.provider.domain.Organization;
import com.laoshiren.hello.elasticsearch.provider.mapper.OrganizationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider
 * ClassName:       ApplicationTest
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/13 17:22
 * Version:         1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private OrganizationMapper mapper;

    @Test
    public void selectAll(){
        LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();

        List<Organization> organizations = mapper.selectList(wrapper);
    }

}
