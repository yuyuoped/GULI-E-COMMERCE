package com.yuyuoped.gmall.wms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.wms.mapper.WareOrderBillMapper;
import com.yuyuoped.gmall.wms.entity.WareOrderBillEntity;
import com.yuyuoped.gmall.wms.service.WareOrderBillService;


@Service("wareOrderBillService")
public class WareOrderBillServiceImpl extends ServiceImpl<WareOrderBillMapper, WareOrderBillEntity> implements WareOrderBillService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<WareOrderBillEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<WareOrderBillEntity>()
        );

        return new PageResultVo(page);
    }

}