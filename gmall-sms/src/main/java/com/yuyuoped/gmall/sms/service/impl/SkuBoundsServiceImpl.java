package com.yuyuoped.gmall.sms.service.impl;

import com.yuyuoped.gmall.sms.entity.SkuFullReductionEntity;
import com.yuyuoped.gmall.sms.entity.SkuLadderEntity;
import com.yuyuoped.gmall.sms.mapper.SkuFullReductionMapper;
import com.yuyuoped.gmall.sms.mapper.SkuLadderMapper;
import com.yuyuoped.gmall.sms.vo.SkuSalesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.sms.mapper.SkuBoundsMapper;
import com.yuyuoped.gmall.sms.entity.SkuBoundsEntity;
import com.yuyuoped.gmall.sms.service.SkuBoundsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("skuBoundsService")
public class SkuBoundsServiceImpl extends ServiceImpl<SkuBoundsMapper, SkuBoundsEntity> implements SkuBoundsService {

    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;
    @Autowired
    private SkuLadderMapper skuLadderMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuBoundsEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuBoundsEntity>()
        );

        return new PageResultVo(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSkuSales(SkuSalesVo skuSalesVo) {
        // 3.1. 积分优惠
        SkuBoundsEntity skuBoundsEntity = new SkuBoundsEntity();
        BeanUtils.copyProperties(skuSalesVo, skuBoundsEntity);
        List<Integer> work = skuSalesVo.getWork();
        if (CollectionUtils.isEmpty(work) && work.size() == 4) {
            skuBoundsEntity.setWork(work.get(3) * 1000 + work.get(2) * 100 + work.get(1)* 10 + work.get(0));
        }
        this.save(skuBoundsEntity);

        // 3.2. 满减优惠
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuSalesVo, skuFullReductionEntity);
        skuFullReductionEntity.setAddOther(skuSalesVo.getFullAddOther());
        this.skuFullReductionMapper.insert(skuFullReductionEntity);

        // 3.3. 数量折扣
        SkuLadderEntity ladderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuSalesVo, ladderEntity);
        ladderEntity.setAddOther(skuSalesVo.getLadderAddOther());
        this.skuLadderMapper.insert(ladderEntity);
    }

}