package com.yuyuoped.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;
import com.yuyuoped.gmall.pms.entity.SpuEntity;
import com.yuyuoped.gmall.pms.vo.SpuVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:28:13
 */
public interface SpuService extends IService<SpuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    PageResultVo queryPageByCategoryId(Long cId, PageParamVo paramVo);

    void bigSave(SpuVo spuVo);
}

