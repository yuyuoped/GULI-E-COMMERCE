package com.yuyuoped.gmall.oms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.oms.mapper.OrderReturnApplyMapper;
import com.yuyuoped.gmall.oms.entity.OrderReturnApplyEntity;
import com.yuyuoped.gmall.oms.service.OrderReturnApplyService;


@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<OrderReturnApplyEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<OrderReturnApplyEntity>()
        );

        return new PageResultVo(page);
    }

}