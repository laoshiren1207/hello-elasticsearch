package com.laoshiren.hello.elasticsearch.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.elasticsearch.provider.domain.TbUser;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider
 * ClassName:       ESClient
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     ElasticSearch
 * Date:            2020/7/9 16:12
 * Version:         1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ESClient {

    @Test
    public void runEmpty(){

    }

    @Resource
    private RestHighLevelClient client;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void initClient(){
        System.out.println(client);
    }

    /**
     * 创建索引的请求
     */
    @Test
    public void createIndex() throws IOException {
        // 索引请求
        CreateIndexRequest request = new CreateIndexRequest("organization");
        // 执行
        CreateIndexResponse response = client.indices()
                .create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    /**
     * 判断索引存不存在
     */
    @Test
    public void existsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("organization");
        boolean exists = client.indices()
                .exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取索引
     */
    @Test
    public void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("tb_user");
        GetIndexResponse response = client.indices()
                .get(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException{
        DeleteIndexRequest request = new DeleteIndexRequest("tb_user");
        AcknowledgedResponse delete = client.indices()
                .delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 创建文档
     */
    @Test
    public void createDoc() throws Exception {
        TbUser tbUser = new TbUser();
        tbUser.setCustomerNo("0001")
                .setGrpContNo("2020")
                .setFirstName("laoshiren1207")
                .setTransAmt(900)
                .setCreateDate(new Date());
        // 指定索引
        IndexRequest request = new IndexRequest("tb_user");
        // 设置规则
        request.id("1")
                .timeout(TimeValue.timeValueSeconds(5));
        // 对象转换json
        request.source(objectMapper.writeValueAsString(tbUser), XContentType.JSON);
        // 发送请求
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        // 命令返回的状态
        System.out.println(index.status());
    }

    /**
     * 文档存不存在
     */
    @Test
    public void existsDoc()throws Exception{
        GetRequest request = new GetRequest("tb_user","1");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文档
     */
    @Test
    public void getDoc() throws Exception{
        GetRequest request = new GetRequest("tb_user","1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 所有信息
        System.out.println(response.toString());
        // 获取doc
        System.out.println(response.getSourceAsString());
    }

    /**
     * 更新文档
     */
    @Test
    public void postDocForUpdate() throws Exception {
        UpdateRequest request = new UpdateRequest("tb_user","1");
        request.timeout(TimeValue.timeValueSeconds(5));
        // 新对象
        TbUser tbUser = new TbUser();
        // 202007111030
        tbUser.setCreateDate(new Date());
        // 文档类型 XContentType
        request.doc(objectMapper.writeValueAsString(tbUser),XContentType.JSON);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);

        System.out.println(update.status());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws Exception{
        DeleteRequest request = new DeleteRequest("tb_user","1");
        request.timeout(TimeValue.timeValueSeconds(5));
        DeleteResponse delete = client.delete(request,RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    /**
     * 批量操作
     */
    @Test
    public void bulkInsert()throws Exception{
        BulkRequest bulkRequest = new BulkRequest();
        // 批量操作
        bulkRequest.timeout(TimeValue.timeValueSeconds(30));

        List<TbUser> list = new ArrayList<>();
        TbUser tbUser =new TbUser();
        tbUser.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("laoshiren")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser);
        TbUser tbUser1 =new TbUser();
        tbUser1.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("项德华")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser1);
        TbUser tbUser2 =new TbUser();
        tbUser2.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("周杰伦")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser2);
        TbUser tbUser3 =new TbUser();
        tbUser3.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("钥世圈")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser3);
        TbUser tbUser4 =new TbUser();
        tbUser4.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("曹晋")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser4);
        TbUser tbUser5 =new TbUser();
        tbUser5.setCreateDate(new Date())
                .setTransAmt(23)
                .setFirstName("杨运")
                .setGrpContNo("00001")
                .setCustomerNo("00003");
        list.add(tbUser5);


        // 获取索引
        for (int i = 0; i< list.size(); i++) {
            //批处理请求
            IndexRequest index = new IndexRequest("tb_user")
                    .id("" + (i + 2))
                    // 转换json string
                    .source(objectMapper.writeValueAsString(list.get(i)), XContentType.JSON);
            bulkRequest.add(index);
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
        System.out.println(bulk.hasFailures());
    }

    /**
     * 搜索
     */
    @Test
    public void search() throws Exception{
        SearchRequest request = new SearchRequest("tb_user");
        // 构造
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 精确匹配 QueryBuilders 工具类
        // 中文或者自定义字符串就要加上 field.keyword
        TermQueryBuilder termQuery = QueryBuilders.termQuery("firstName", "a");
        // 构建所需查询
        builder.query(termQuery);
        builder.from(0);
        builder.size(5);
        builder.timeout(new TimeValue(60,TimeUnit.SECONDS));
        request.source(builder);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        System.out.println(search.toString());
        // 所需数据
        System.out.println(search.getHits());
        System.out.println("---");
        for (SearchHit hit : search.getHits().getHits()) {
            String s = objectMapper.writeValueAsString(hit);
            System.out.println(s);
        }

    }

}
