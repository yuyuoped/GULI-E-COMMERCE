package com.yuyuoped.gmall.pms.vo;

import com.yuyuoped.gmall.pms.entity.SkuAttrValueEntity;
import com.yuyuoped.gmall.pms.entity.SkuEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuyuoped
 * @since 2022/7/28
 */
@Data
public class SkuVo extends SkuEntity {
    // 积分优惠信息
    //成长积分
    private BigDecimal growBounds;
    //购物积分
    private BigDecimal buyBounds;
    /* 4位置分别表示
     无优惠，成长积分是否生效
     无优惠，购物积分是否生效
     有优惠，成长积分是否生效
     有优惠，购物积分是否生效
     */
    private List<Integer> work;

    // 打折优惠信息
    //满fullCount打discount折
    private Integer fullCount;
    private BigDecimal discount;
    //是否和其他折扣通用 1：通用 0:不同用
    private Integer ladderAddOther;

    // 满减优惠信息
    //满fullPrice减reducePrice
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    //是否和其他折扣通用 1：通用
    private Integer fullAddOther;

    // sku图片列表
    private List<String> images;

    // sku的销售属性
    private List<SkuAttrValueEntity> saleAttrs;
}
