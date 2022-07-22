package com.yuyuoped.gmall.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.sms.mapper.SeckillSkuMapper;
import com.yuyuoped.gmall.sms.entity.SeckillSkuEntity;
import com.yuyuoped.gmall.sms.service.SeckillSkuService;


@Service("seckillSkuService")
public class SeckillSkuServiceImpl extends ServiceImpl<SeckillSkuMapper, SeckillSkuEntity> implements SeckillSkuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SeckillSkuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SeckillSkuEntity>()
        );

        return new PageResultVo(page);
    }

}