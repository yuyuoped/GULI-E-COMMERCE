package com.yuyuoped.gmall.pms.vo;

import com.yuyuoped.gmall.pms.entity.SpuEntity;
import lombok.Data;

import java.util.List;

/**
 * @author yuyuoped
 * @since
 */
@Data
public class SpuVo extends SpuEntity {

    private List<String> spuImages;

    private List<SpuAttrValueVo> baseAttrs;

    private List<SkuVo> skus;
}
