package com.yuyuoped.gmall.oms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.oms.mapper.OrderItemMapper;
import com.yuyuoped.gmall.oms.entity.OrderItemEntity;
import com.yuyuoped.gmall.oms.service.OrderItemService;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<OrderItemEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageResultVo(page);
    }

}