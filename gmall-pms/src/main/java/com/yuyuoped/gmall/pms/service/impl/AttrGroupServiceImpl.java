package com.yuyuoped.gmall.pms.service.impl;

import com.yuyuoped.gmall.pms.entity.AttrEntity;
import com.yuyuoped.gmall.pms.mapper.AttrMapper;
import com.yuyuoped.gmall.pms.mapper.SpuAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.pms.mapper.AttrGroupMapper;
import com.yuyuoped.gmall.pms.entity.AttrGroupEntity;
import com.yuyuoped.gmall.pms.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrGroupMapper groupMapper;

    @Autowired
    private AttrMapper attrMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<AttrGroupEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public List<AttrGroupEntity> getByCategoryId(Long cId) {
        return groupMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("category_id", cId));
    }

    @Override
    public Collection<AttrGroupEntity> queryAttrsByCategoryId(Long categoryId) {
        //获取所有categoryId的attrGroup
        List<AttrGroupEntity> attrGroupEntityList = this.getByCategoryId(categoryId);

        //将其转换为key为自己id，value为自身的map
        Map<Long, AttrGroupEntity> groupMap = attrGroupEntityList.stream().collect(Collectors.toMap(AttrGroupEntity::getId, attrGroupEntity -> attrGroupEntity));

        //获取所有categoryId的attr
        List<AttrEntity> attrEntityList = attrMapper.selectList(new QueryWrapper<AttrEntity>().eq("category_id", categoryId).eq("type", 1));

        //将所有attr通过groupId赋给相应的attrgroup
        attrEntityList.forEach(attrEntity -> {
            groupMap.get(attrEntity.getGroupId()).addAttrEntity(attrEntity);
        });

        return groupMap.values();
    }

}