package com.laoshiren.hello.elasticsearch.provider.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * ProjectName:     hello-elasticsearch
 * Package:         com.laoshiren.hello.elasticsearch.provider.domain
 * ClassName:       Organization
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     ${description}
 * Date:            2020/7/13 16:57
 * Version:         1.0.0
 */
@Data
@TableName(value = "organization")
public class Organization implements Serializable {
    /**
     * 主键，机构id
     */
    @TableId(value = "org_id", type = IdType.AUTO)
    private Long orgId;

    /**
     * 机构编码
     */
    @TableField(value = "org_code")
    private String orgCode;

    /**
     * 机构名称
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 机构类型：1、医院；2、电商；3、连锁；4、药店；5、政府；6、供应商
     */
    @TableField(value = "org_type")
    private Integer orgType;

    /**
     * 机构电话
     */
    @TableField(value = "org_tel")
    private String orgTel;

    /**
     * 机构地址
     */
    @TableField(value = "org_address")
    private String orgAddress;

    /**
     * 机构省级编码id
     */
    @TableField(value = "org_id_province")
    private Integer orgIdProvince;

    /**
     * 机构市级编码id
     */
    @TableField(value = "org_id_city")
    private Integer orgIdCity;

    /**
     * 机构区级编码id
     */
    @TableField(value = "org_id_area")
    private Integer orgIdArea;

    /**
     * 机构街道级编码id
     */
    @TableField(value = "org_id_street")
    private String orgIdStreet;

    /**
     * 机构联系人
     */
    @TableField(value = "org_link_man")
    private String orgLinkMan;

    /**
     * 机构传真
     */
    @TableField(value = "org_fax")
    private String orgFax;

    /**
     * 经度
     */
    @TableField(value = "org_lng")
    private String orgLng;

    /**
     * 维度
     */
    @TableField(value = "org_lat")
    private String orgLat;

    /**
     * 机构父级id，最高级为0
     */
    @TableField(value = "org_id_father")
    private Integer orgIdFather;

    /**
     * 机构医保编码
     */
    @TableField(value = "org_code_yibao")
    private String orgCodeYibao;

    /**
     * 是否是医保门店：0，非医保门店；1，医保门店；
     */
    @TableField(value = "org_yibao")
    private String orgYibao;

    /**
     * 机构是否可用：0，不可用；1，可用；
     */
    @TableField(value = "org_enable")
    private Integer orgEnable;

    /**
     * 机构营业时间
     */
    @TableField(value = "org_opening_hours")
    private String orgOpeningHours;

    /**
     * 机构营业执照
     */
    @TableField(value = "org_licence")
    private String orgLicence;

    /**
     * 机构经营范围
     */
    @TableField(value = "org_sale_range")
    private String orgSaleRange;

    /**
     * 机构法人代表
     */
    @TableField(value = "org_corporate")
    private String orgCorporate;

    /**
     * 机构企业网站
     */
    @TableField(value = "org_web_site")
    private String orgWebSite;

    /**
     * 机构注册资金
     */
    @TableField(value = "org_reg_capital")
    private String orgRegCapital;

    /**
     * 机构简介
     */
    @TableField(value = "org_introduce")
    private String orgIntroduce;

    /**
     * 逻辑删除：0，已删除；1，未删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ORG_ID = "org_id";

    public static final String COL_ORG_CODE = "org_code";

    public static final String COL_ORG_NAME = "org_name";

    public static final String COL_ORG_TYPE = "org_type";

    public static final String COL_ORG_TEL = "org_tel";

    public static final String COL_ORG_ADDRESS = "org_address";

    public static final String COL_ORG_ID_PROVINCE = "org_id_province";

    public static final String COL_ORG_ID_CITY = "org_id_city";

    public static final String COL_ORG_ID_AREA = "org_id_area";

    public static final String COL_ORG_ID_STREET = "org_id_street";

    public static final String COL_ORG_LINK_MAN = "org_link_man";

    public static final String COL_ORG_FAX = "org_fax";

    public static final String COL_ORG_LNG = "org_lng";

    public static final String COL_ORG_LAT = "org_lat";

    public static final String COL_ORG_ID_FATHER = "org_id_father";

    public static final String COL_ORG_CODE_YIBAO = "org_code_yibao";

    public static final String COL_ORG_YIBAO = "org_yibao";

    public static final String COL_ORG_ENABLE = "org_enable";

    public static final String COL_ORG_OPENING_HOURS = "org_opening_hours";

    public static final String COL_ORG_LICENCE = "org_licence";

    public static final String COL_ORG_SALE_RANGE = "org_sale_range";

    public static final String COL_ORG_CORPORATE = "org_corporate";

    public static final String COL_ORG_WEB_SITE = "org_web_site";

    public static final String COL_ORG_REG_CAPITAL = "org_reg_capital";

    public static final String COL_ORG_INTRODUCE = "org_introduce";

    public static final String COL_IS_DELETED = "is_deleted";
}