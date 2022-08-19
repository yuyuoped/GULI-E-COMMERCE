package com.yuyuoped.gmall.pms.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Goods {

    // sku相关字段
    private Long skuId;
    private String title;
    private String subtitle;
    private String defaultImage;
    private Double price;

    // 排序所需的字段：
    private Long sales = 0L; // 销量排序所需字段
    private Date createTime; // 新品排序所需字段
    private Boolean store = false; // 是否有货的过滤字段

    /// 过滤所需字段：
    // 品牌所需的字段
    private Long brandId;
    private String brandName;
    private String logo;

    // 分类所需的字段：
    private Long categoryId;
    private String categoryName;
}
