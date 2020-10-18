package com.laoshiren.hello.elasticsearch.provider.api;

import com.github.pagehelper.PageInfo;
import com.laoshiren.hello.elasticsearch.provider.domain.Organization;
import com.laoshiren.hello.elasticsearch.provider.domain.MyPageInfo;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider
 * ClassName:       OrganizationService
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/13 18:01
 * Version:         1.0.0
 */
public interface OrganizationService {

    PageInfo<Organization> selectPage(int pageNum,int pageSize,Organization organization);

    MyPageInfo<Organization> queryOrganizationHighLight(int pageNum,
                                                        int pageSize,
                                                        String keyword) throws Exception;

}
