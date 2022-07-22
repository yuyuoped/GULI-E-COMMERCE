package com.yuyuoped.gmall.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.sms.mapper.SeckillSkuNoticeMapper;
import com.yuyuoped.gmall.sms.entity.SeckillSkuNoticeEntity;
import com.yuyuoped.gmall.sms.service.SeckillSkuNoticeService;


@Service("seckillSkuNoticeService")
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeMapper, SeckillSkuNoticeEntity> implements SeckillSkuNoticeService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SeckillSkuNoticeEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SeckillSkuNoticeEntity>()
        );

        return new PageResultVo(page);
    }

}