package com.laoshiren.hello.elasticsearch.provider.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laoshiren.hello.elasticsearch.provider.api.OrganizationService;
import com.laoshiren.hello.elasticsearch.provider.domain.Organization;
import com.laoshiren.hello.elasticsearch.provider.domain.MyPageInfo;
import com.laoshiren.hello.elasticsearch.provider.mapper.OrganizationMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.api.impl
 * ClassName:       OrganizationServiceImpl
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/13 18:02
 * Version:         1.0.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationMapper mapper;
    @Resource
    private RestHighLevelClient client;
    @Resource
    private ObjectMapper jsonMapper;

    @Override
    public PageInfo<Organization> selectPage(int pageNum, int pageSize, Organization organization) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Organization> wrapper = new QueryWrapper<>();
        wrapper.eq(Organization.COL_IS_DELETED,0);
        List<Organization> organizations = mapper.selectList(wrapper);
        organizations.forEach(it-> {
            it.setIsDeleted(1);
            mapper.updateById(it);
        });
        return new PageInfo<>(organizations);
    }

    @Override
    public MyPageInfo<Organization> queryOrganizationHighLight(int pageNum, int pageSize,String keyword) throws Exception {
        // ES
        if (keyword==null || ("".equals(keyword)) ) {
            return new MyPageInfo<>();
        }
        // request
        SearchRequest request = new SearchRequest("organization");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder query = QueryBuilders.matchQuery("orgName", keyword);
        builder.query(query);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // highlight
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("orgName").requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        // 数据
        List<Organization> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField nameField = highlightFields.get("orgName");
            if(nameField!=null){
                Text[] fragments = nameField.fragments();
                StringBuilder nameTmp = new StringBuilder();
                for(Text text:fragments){
                    nameTmp.append(text);
                }
                // 高亮字段
                source.put("orgName", nameTmp.toString());
            }
            // map2Entity
            Organization organization = jsonMapper.convertValue(source, Organization.class);
            list.add(organization);
        }

        return new MyPageInfo<Organization>().getMyPageInfo(list,pageNum,pageSize);
    }
}
