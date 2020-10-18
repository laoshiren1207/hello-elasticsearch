package com.laoshiren.hello.elasticsearch.provider.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider
 * ClassName:       TbUser
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/9 17:19
 * Version:         1.0.0
 */
@Data
@Accessors(chain = true)
public class TbUser {

    private String grpContNo;
    private String customerNo;
    private String firstName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    private long transAmt;

}
