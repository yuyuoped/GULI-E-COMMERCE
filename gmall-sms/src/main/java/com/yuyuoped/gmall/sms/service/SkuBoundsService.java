package com.yuyuoped.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;
import com.yuyuoped.gmall.sms.entity.SkuBoundsEntity;
import com.yuyuoped.gmall.sms.vo.SkuSalesVo;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:29:34
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    void saveSkuSales(SkuSalesVo skuSalesVo);
}

