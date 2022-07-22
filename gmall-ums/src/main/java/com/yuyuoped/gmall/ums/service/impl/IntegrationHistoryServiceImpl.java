package com.yuyuoped.gmall.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.ums.mapper.IntegrationHistoryMapper;
import com.yuyuoped.gmall.ums.entity.IntegrationHistoryEntity;
import com.yuyuoped.gmall.ums.service.IntegrationHistoryService;


@Service("integrationHistoryService")
public class IntegrationHistoryServiceImpl extends ServiceImpl<IntegrationHistoryMapper, IntegrationHistoryEntity> implements IntegrationHistoryService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<IntegrationHistoryEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<IntegrationHistoryEntity>()
        );

        return new PageResultVo(page);
    }

}