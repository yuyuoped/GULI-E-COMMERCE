package com.yuyuoped.gmall.wms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.wms.mapper.PurchaseDetailMapper;
import com.yuyuoped.gmall.wms.entity.PurchaseDetailEntity;
import com.yuyuoped.gmall.wms.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<PurchaseDetailEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<PurchaseDetailEntity>()
        );

        return new PageResultVo(page);
    }

}