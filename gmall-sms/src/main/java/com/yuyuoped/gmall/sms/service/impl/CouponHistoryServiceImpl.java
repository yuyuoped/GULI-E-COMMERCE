package com.yuyuoped.gmall.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.sms.mapper.CouponHistoryMapper;
import com.yuyuoped.gmall.sms.entity.CouponHistoryEntity;
import com.yuyuoped.gmall.sms.service.CouponHistoryService;


@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistoryEntity> implements CouponHistoryService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<CouponHistoryEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<CouponHistoryEntity>()
        );

        return new PageResultVo(page);
    }

}