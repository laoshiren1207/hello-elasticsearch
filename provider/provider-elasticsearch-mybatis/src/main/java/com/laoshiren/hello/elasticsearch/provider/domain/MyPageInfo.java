package com.laoshiren.hello.elasticsearch.provider.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.domain.vo
 * ClassName:       MyPageInfo
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/14 10:50
 * Version:         1.0.0
 */
@Data
public class MyPageInfo<T> implements Serializable {

    private static final long serialVersionUID = -2209915315585837539L;
    private long total;
    private int pageNum;
    private int pageSize;
    private int size;
    private int pages;
    private List<T> data;


    public MyPageInfo<T> getMyPageInfo(List<T> data,int pageNum,int pageSize){
        int currentPage = pageNum == 0 ? 0 : pageNum-1;
        MyPageInfo<T> page = new MyPageInfo<>();
        page.pageNum = pageNum;
        page.pageSize = pageSize;
        page.total = data.size();
        page.pages = pages(data,pageSize);

        if ((data.size() - currentPage*pageSize > pageSize)) {
            page.data = data.subList(currentPage * pageSize, currentPage * pageSize+pageSize);
        } else {
            page.data = data.subList(currentPage * pageSize, data.size());
        }
        return page;
    }

    private int pages(List<T> data,int pageSize){
        int size = data.size() % pageSize;
        if (size != 0) {
            size++;
        }
        return size;
    }

}
