package com.yuyuoped.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;
import com.yuyuoped.gmall.pms.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:28:13
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

