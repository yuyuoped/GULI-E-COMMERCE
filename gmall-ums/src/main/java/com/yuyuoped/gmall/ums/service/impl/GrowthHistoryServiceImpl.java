package com.yuyuoped.gmall.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.ums.mapper.GrowthHistoryMapper;
import com.yuyuoped.gmall.ums.entity.GrowthHistoryEntity;
import com.yuyuoped.gmall.ums.service.GrowthHistoryService;


@Service("growthHistoryService")
public class GrowthHistoryServiceImpl extends ServiceImpl<GrowthHistoryMapper, GrowthHistoryEntity> implements GrowthHistoryService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<GrowthHistoryEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<GrowthHistoryEntity>()
        );

        return new PageResultVo(page);
    }

}