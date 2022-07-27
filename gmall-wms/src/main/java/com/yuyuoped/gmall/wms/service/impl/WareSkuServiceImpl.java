package com.yuyuoped.gmall.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.wms.mapper.WareSkuMapper;
import com.yuyuoped.gmall.wms.entity.WareSkuEntity;
import com.yuyuoped.gmall.wms.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareSkuMapper wareSkuMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<WareSkuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public List<WareSkuEntity> getBySkuId(Long id) {
        return wareSkuMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", id));
    }

}