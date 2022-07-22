package com.yuyuoped.gmall.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.sms.mapper.MemberPriceMapper;
import com.yuyuoped.gmall.sms.entity.MemberPriceEntity;
import com.yuyuoped.gmall.sms.service.MemberPriceService;


@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<MemberPriceEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<MemberPriceEntity>()
        );

        return new PageResultVo(page);
    }

}