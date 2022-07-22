package com.yuyuoped.gmall.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.pms.mapper.CommentReplayMapper;
import com.yuyuoped.gmall.pms.entity.CommentReplayEntity;
import com.yuyuoped.gmall.pms.service.CommentReplayService;


@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplayEntity> implements CommentReplayService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<CommentReplayEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<CommentReplayEntity>()
        );

        return new PageResultVo(page);
    }

}