package com.yuyuoped.gmall.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.pms.mapper.SkuMapper;
import com.yuyuoped.gmall.pms.entity.SkuEntity;
import com.yuyuoped.gmall.pms.service.SkuService;


@Service("skuService")
public class SkuServiceImpl extends ServiceImpl<SkuMapper, SkuEntity> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public List<SkuEntity> listBySpuId(Long id) {
        return skuMapper.selectList(new QueryWrapper<SkuEntity>().eq("spu_id", id));
    }

}