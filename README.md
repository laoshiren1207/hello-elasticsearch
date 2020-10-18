## ElasticSearch

~~~yaml 
version: '3.1'                          
services:
  elasticsearch:
    image: daocloud.io/library/elasticsearch:7.6.2
    restart: always
    container_name: elasticsearch
    ports:
      - 9200:9200
    environment:
      discovery.type: single-node

  # kibana图形化插件
  kibana:
    image:  daocloud.io/library/kibana:7.6.2
    restart: always
    environment:
      SERVER_NAME: kibana
      ELASTICSEARCH_URL: http://192.168.8.4:9200
    ports:
      - 5601:5601
    depends_on:
      -  elasticsearch
~~~
启动
~~~shell
curl http://127.0.0.1:9200
# 输出
{
  "name" : "82877e7f04ad",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "x-bP78brST-9y8kpLUtrxw",
  "version" : {
    "number" : "7.6.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "ef48eb35cf30adf4db14086e8aabd07ef6fb113f",
    "build_date" : "2020-03-26T06:34:37.794943Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
~~~
[Kibana](http://192.168.8.4:5601)

### es
> 文档 （相当于一条数据）

![1594197390062](C:\Users\15207\AppData\Roaming\Typora\typora-user-images\1594197390062.png)

ES RESTFul

| method | url地址 | 描述 |
| :----: | :----: | :----: |
| PUT | :9200/索引名称/类型名称/文档id | 创建文档 |
| POST | :9200/索引名称/类型名称 | 创建文档随机id |
| POST | :9200/索引名称/类型名称/文档id/_update | 修改文档 |
| DELETE | :9200/索引名称/类型名称/文档id | 删除文档 |
| GET | :9200/索引名称/类型名称/文档id | 查询 |
| POST | :9200/索引名称/类型名称/_search | 查询所有数据 |

创建一个索引

![1594207368293](C:\Users\15207\AppData\Roaming\Typora\typora-user-images\1594207368293.png)

~~~json
{
  "_index" : "test1",
  "_type" : "type",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",  
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
~~~

~~~bash
PUT /test1/type/3
{
  "name":"C9g",
  "age" :3,
  "trueName":"王艺枨"
}

GET /test1/type/1

POST /test1/type/1/_update
{
  "doc": {
    "trueName": "项德华"
  }
}

GET /test1/type/4

## 返回
{
  "_index" : "test1",
  "_type" : "type",
  "_id" : "1",
  "_version" : 3,
  "_seq_no" : 3,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "laoshiren",
    "age" : 3,
    "trueName" : "项德华"
  }
}
~~~

基本查询只能字段是`text`才能分词`keyword`不行

![1594260297422](C:\Users\15207\AppData\Roaming\Typora\typora-user-images\1594260297422.png)






