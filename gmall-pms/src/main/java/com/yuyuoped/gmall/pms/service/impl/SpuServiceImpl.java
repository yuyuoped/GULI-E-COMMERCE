package com.yuyuoped.gmall.pms.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.pms.mapper.SpuMapper;
import com.yuyuoped.gmall.pms.entity.SpuEntity;
import com.yuyuoped.gmall.pms.service.SpuService;
import org.springframework.util.StringUtils;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo queryPageByCategoryId(Long cId, PageParamVo paramVo) {
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        if (cId != 0) {
            wrapper.eq("category_id", cId);
        }

        if (!StringUtils.isEmpty(paramVo.getKey())) {
            wrapper.and(w -> w.like("name", paramVo.getKey())
                    .or()
                    .like("id", paramVo.getKey()));
        }

        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                wrapper
        );

        return new PageResultVo(page);
    }

}